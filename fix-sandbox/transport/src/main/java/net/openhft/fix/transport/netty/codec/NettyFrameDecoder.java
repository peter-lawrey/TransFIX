/*
 * Copyright 2013 Peter Lawrey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.openhft.fix.transport.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author lburgazzoli
 */
public class NettyFrameDecoder extends ByteToMessageDecoder {

    private int m_msgLength;

    /**
     * c-tor
     */
    public NettyFrameDecoder() {
        m_msgLength = -1;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        while(doDecode(ctx,in,out)) {
            // EMPTY
        }
    }

    /**
     *
     * @param ctx
     * @param in
     * @param out
     * @return
     */
    private boolean doDecode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        boolean hasMoreData = false;

        if(m_msgLength == -1) {
            if(canDecode(in)) {
                int ridx  = in.readerIndex();
                int bssohidx = in.indexOf(ridx     ,ridx + 12,NettyFrameHelper.BYTE_SOH);
                int blsohidx = in.indexOf(ridx + 12,ridx + 20,NettyFrameHelper.BYTE_SOH);

                // check the existence of:
                // - BeginString 8=
                // - BodyLength  9=
                if( in.getByte(ridx)          == NettyFrameHelper.BYTE_BEGIN_STRING &&
                    in.getByte(ridx + 1)      == NettyFrameHelper.BYTE_EQUALS       &&
                    in.getByte(bssohidx + 1)  == NettyFrameHelper.BYTE_BODY_LENGTH  &&
                    in.getByte(bssohidx + 2)  == NettyFrameHelper.BYTE_EQUALS       ) {

                    int bodyLength = 0;
                    for(int i=bssohidx+3;i<blsohidx;i++) {
                        bodyLength *= 10;
                        bodyLength += ((int)in.getByte(i) - (int)'0');
                    }

                    m_msgLength = 1 + bodyLength + (blsohidx - ridx) + NettyFrameHelper.MSG_CSUM_LEN;
                } else {
                    //TODO: add some useful information to the exeption
                    throw new Error("Unexpected state (header)");
                }
            }
        }

        if(m_msgLength != -1 && in.readableBytes() >= m_msgLength){
            if(in.readableBytes() >= m_msgLength) {
                ByteBuf rv = ctx.alloc().buffer(m_msgLength);
                in.readBytes(rv,m_msgLength);

                //TODO: validate checksum
                out.add(rv);

                m_msgLength = -1;
                hasMoreData = canDecode(in);
            } else {
                //TODO: add some useful information to the exeption
                throw new Error("Unexpected state (body)");
            }
        }

        return hasMoreData;
    }

    /**
     *
     * @param in
     * @return
     */
    private boolean canDecode(final ByteBuf in) {
        return in.readableBytes() >= NettyFrameHelper.MSG_MIN_BYTES;
    }
}
