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

package net.openhft.fix.transport.codec;

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
        doDecode(in,out);
    }

    /**
     * TODO: loop for more messages in the ByteBuf
        */
    void doDecode(ByteBuf in, List<Object> out) {
        if(m_msgLength == -1) {
            if(in.readableBytes() >= NettyFrameHelper.MSG_MIN_BYTES) {
                //int rindex = in.readerIndex();

                int bsi = in.indexOf(0,12,NettyFrameHelper.BYTE_SOH);
                int bli = in.indexOf(12,20,NettyFrameHelper.BYTE_SOH);

                // check the existence of:
                // - BeginString 8=
                // - BodyLength  9=
                if( in.getByte(0)       == NettyFrameHelper.BYTE_BEGIN_STRING &&
                    in.getByte(1)       == NettyFrameHelper.BYTE_EQUALS       &&
                    in.getByte(bsi + 1) == NettyFrameHelper.BYTE_BODY_LENGTH  &&
                    in.getByte(bsi + 2) == NettyFrameHelper.BYTE_EQUALS       ) {

                    int bodyLength = 0;
                    for(int i=bsi+3;i<bli;i++) {
                        bodyLength *= 10;
                        bodyLength += ((int)in.getByte(i) - (int)'0');
                    }

                    m_msgLength = 1 + bodyLength + bli + NettyFrameHelper.MSG_CSUM_LEN;
                } else {
                    throw new Error("Unexpected state (header)");
                }
            }
        }

        if(m_msgLength != -1 && in.readableBytes() >= m_msgLength){
            if(in.readableBytes() >= m_msgLength) {
                byte[] rv = new byte[m_msgLength];
                in.readBytes(rv);
                in.discardReadBytes();

                //TODO: validate checksum
                out.add(rv);

                m_msgLength = -1;
            } else {
                throw new Error("Unexpected state (body)");
            }
        }
    }
}
