/*
 * Copyright 2013 peter.lawrey Lawrey
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

package net.openhft.fix.include.util;

import net.openhft.compiler.CachedCompiler;
import net.openhft.fix.compiler.FieldLookup;
import net.openhft.fix.include.v42.*;
import net.openhft.lang.collection.HugeArray;
import net.openhft.lang.io.ByteBufferBytes;
import net.openhft.lang.io.Bytes;
import net.openhft.lang.model.Byteable;
import net.openhft.lang.model.DataValueGenerator;

import java.nio.ByteBuffer;

/**
 * Default configuration class for a Fix 4.2 message. Uses static methods to initialize FIX 4.2
 * header and fields. The main purpose of this class is to initialize standard fix 4.2 fields
 * to allocate memory before usage to avoid dynamic allocation of ByteBufferBytes during runtime
 * for FIX fields data.
 */
public class FixConfig implements Cloneable {

    private int fixVersionMajor;
    private int fixVersionMinor;
    private int fixVersionServicePack;

    private int currentFixVersion;
    private int fix4_2_0_mask = 0x01A4;
    private int fix5_0_0_mask = 0x01F4;
    private final DataValueGenerator dvg = new DataValueGenerator();
    private Trailer trailer;
    private Header header;
    private Messages messages;
    private Components components;
    private Fields fields;


    public FixConfig clone() {
        try {
            return (FixConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public static FixConfig SERVER_DEFAULT_4_2 = new FixConfig()
            .setFixVersionMajor(4)
            .setFixVersionMinor(2)
            .setFixVersionServicePack(0)
                                                /*.createServerFixHeader()
												.createServerFixMessages()
												.createServerFixTrailer()
												.createServerFixComponents()*/
            .createServerFixFields();

    /**
     * Static factory method to initializes FIX message header to default Fix 4.2 version
     *
     * @return -FixConfig
     */
    public FixConfig createServerFixHeader() {
        if ((currentFixVersion & fix4_2_0_mask) != 0) {
            try {
                load42DefaultHeader();
            } catch (Exception e) {
                throw new AssertionError(e);
            }
        }
        return this;
    }

    private void load42DefaultHeader() throws Exception {
        //this.header = (Header) dvg.nativeInstance(FixMessageType.class);

        String actual = new DataValueGenerator().generateNativeObject(FixMessageType.class);
        CachedCompiler cc = new CachedCompiler(null, null);
        Class<?> aClass = cc.loadFromJava(FixMessageType.class.getName() + "$$Native", actual);

        @SuppressWarnings("unchecked")
        FixMessageType fmt = (FixMessageType) aClass.asSubclass(FixMessageType.class).newInstance();
        Bytes bytes = new ByteBufferBytes(ByteBuffer.allocate(1024 * 1024));
        ((Byteable) fmt).bytes(bytes, 0L);

        this.header = (Header) fmt;
        //this.header = new Header();
        HugeArray<Field> array = this.header.setFieldSize(27).getField();
        Field field = null;
        for (short i = 0; i < FixConstants.headerFieldName.length; i++) {
            field = array.get(i);
            field.setName(FixConstants.headerFieldName[i]);
            if (i < 5) {
                field.setRequired("Y");
            } else {
                field.setRequired("N");
            }
        }
    }

    /**
     * Static factory method to initializes all the standard FIX &lt;Messages&gt; of FIX 4.2 version
     *
     * @return -FixConfig
     */
    public FixConfig createServerFixMessages() {
        if ((currentFixVersion & fix4_2_0_mask) != 0) {
            load42DefaultMessages();
        }
        return this;
    }

    private void load42DefaultMessages() {
        //this.messages = dvg.nativeInstance(Messages.class);
        this.messages = new Messages();
        //default is 46 messages
        HugeArray<Message> array = this.messages.setMessagesSize(46).getMessage();
        Message message = null;

        for (short i = 0; i < FixConstants.messsagesMsgName.length; i++) {
            message = array.get(i);
            message.setName(FixConstants.messsagesMsgName[i]);
            message.setMsgtype(FixConstants.messagesMsgType[i]);
            populateMessage(i, message);
        }
    }

    private void populateMessage(int msgNo, Message message) {
        HugeArray<Field> arrayField = null;
        HugeArray<Group> arrayGroup = null;
        HugeArray<Group> arrayInnerGroup = null;
        int[] fieldYesArray = null;
        String[] fieldArray = null;
        int[] groupYesArray = null;
        String[] groupArray = null;
        int[] groupFieldSize = null;
        int[][] groupFieldYesArray = null;
        String[] groupField = null;
        int[] innerGroupFieldSize = null;
        String[] innerGroupField = null;
        String[] innerGroup = null;

        switch (msgNo) {
            case 0:
                message.setMsgcat(FixConstants.messagesMsgCat[0]);
                arrayField = null;
                arrayField = message.setFieldSize(1).getField();
                arrayField.get(0).setName(FixConstants.MESSAGES_TEST_REQID_FN);
                arrayField.get(0).setRequired("N");
            case 1:
                message.setMsgcat(FixConstants.messagesMsgCat[0]);
                arrayField = null;
                arrayField = message.setFieldSize(1).getField();
                arrayField.get(0).setName(FixConstants.MESSAGES_TEST_REQID_FN);
                arrayField.get(0).setRequired("Y");
            case 2:
                message.setMsgcat(FixConstants.messagesMsgCat[0]);
                arrayField = null;
                arrayField = message.setFieldSize(FixConstants.messagesResendReq.length).getField();
                for (short i = 0; i < arrayField.length(); i++) {
                    arrayField.get(i).setName(FixConstants.messagesResendReq[i]);
                    arrayField.get(i).setRequired("Y");
                }
            case 3:
                message.setMsgcat(FixConstants.messagesMsgCat[0]);
                arrayField = null;
                arrayField = message.setFieldSize(FixConstants.messagesReject.length).getField();
                for (short i = 0; i < arrayField.length(); i++) {
                    arrayField.get(i).setName(FixConstants.messagesReject[i]);
                    if (i == 0) {
                        arrayField.get(i).setRequired("Y");
                    } else {
                        arrayField.get(i).setRequired("N");
                    }
                }
            case 4:
                message.setMsgcat(FixConstants.messagesMsgCat[0]);
                arrayField = null;
                arrayField = message.setFieldSize(FixConstants.messagesSeqReset.length).getField();
                for (short i = 0; i < arrayField.length(); i++) {
                    arrayField.get(i).setName(FixConstants.messagesSeqReset[i]);
                    if (i == 0) {
                        arrayField.get(i).setRequired("N");
                    } else {
                        arrayField.get(i).setRequired("Y");
                    }
                }
            case 5:
                message.setMsgcat(FixConstants.messagesMsgCat[0]);
                arrayField = null;
                arrayField = message.setFieldSize(FixConstants.messagesLogout.length).getField();
                for (short i = 0; i < arrayField.length(); i++) {
                    arrayField.get(i).setName(FixConstants.messagesLogout[i]);
                    arrayField.get(i).setRequired("N");
                }
            case 6:
                message.setMsgcat(FixConstants.messagesMsgCat[1]);
                arrayField = null;
                arrayGroup = null;
                //default 36
                arrayField = message.setFieldSize(FixConstants.messagesIOI.length).getField();
                arrayGroup = message.setGroupSize(FixConstants.messagesIOIGroup.length).getGroup();
                for (short i = 0; i < arrayField.length(); i++) {
                    arrayField.get(i).setName(FixConstants.messagesIOI[i]);
                    if (i == 0 || i == 1 || i == 3 || i == 22 || i == 23) {
                        arrayField.get(i).setRequired("Y");
                    } else {
                        arrayField.get(i).setRequired("N");
                    }
                }
                for (short i = 0; i < arrayGroup.length(); i++) {
                    arrayGroup.get(i).setName(FixConstants.messagesIOIGroup[i]);
                    arrayGroup.get(i).setRequired("N");
                    arrayField = null;
                    if (i == 0) {
                        arrayField = arrayGroup.get(i).setFieldSize(1).getField();
                        arrayField.get(0).setName(FixConstants.messagesIOIGroupFields[0]);
                        arrayField.get(0).setRequired("N");
                    } else {
                        arrayField = arrayGroup.get(i).setFieldSize(
                                FixConstants.messagesIOIGroupFields.length).getField();
                        for (short j = 0; j < arrayField.length(); j++) {
                            arrayField.get(0).setName(FixConstants.messagesIOIGroupFields[j + 1]);
                            arrayField.get(0).setRequired("N");
                        }
                    }
                }
            case 7:
                message.setMsgcat(FixConstants.messagesMsgCat[1]);
                arrayField = null;
                arrayField = message.setFieldSize(FixConstants.messagesAdv.length).getField();
                for (short i = 0; i < arrayField.length(); i++) {
                    arrayField.get(i).setName(FixConstants.messagesAdv[i]);
                    if (i == 0 || i == 1 || i == 3 || i == 22 || i == 23) {
                        arrayField.get(i).setRequired("Y");
                    } else {
                        arrayField.get(i).setRequired("N");
                    }
                }
            case 8:
                message.setMsgcat(FixConstants.messagesMsgCat[1]);
                arrayField = null;
                arrayGroup = null;
                //default 36
                arrayField = message.setFieldSize(FixConstants.messageExecReport.length).getField();
                arrayGroup = message.setGroupSize(1).getGroup();
                for (short i = 0; i < arrayField.length(); i++) {
                    arrayField.get(i).setName(FixConstants.messageExecReport[i]);
                    if (i == 0 || i == 1 || i == 3 || i == 22 || i == 23) {
                        arrayField.get(i).setRequired("Y");
                    } else {
                        arrayField.get(i).setRequired("N");
                    }
                }
                arrayGroup.get(0).setName(FixConstants.messageExecReportGrp);
                arrayGroup.get(0).setRequired("N");
                arrayField = null;
                arrayField = arrayGroup.get(0).setFieldSize(
                        FixConstants.messageExecReportGrpFlds.length).getField();
                for (short j = 0; j < arrayField.length(); j++) {
                    arrayField.get(0).setName(FixConstants.messageExecReportGrpFlds[j]);
                    arrayField.get(0).setRequired("N");
                }
            case 9:
                message.setMsgcat(FixConstants.messagesMsgCat[1]);
                arrayField = null;
                arrayField = message.setFieldSize(
                        FixConstants.messageOrderCancelRej.length).getField();
                for (short i = 0; i < arrayField.length(); i++) {
                    arrayField.get(i).setName(FixConstants.messageOrderCancelRej[i]);
                    if (i == 0 || i == 2 || i == 3 || i == 4 || i == 10) {
                        arrayField.get(i).setRequired("Y");
                    } else {
                        arrayField.get(i).setRequired("N");
                    }
                }
            case 10://logon
                message.setMsgcat(FixConstants.messagesMsgCat[0]);
                arrayField = null;
                arrayGroup = null;
                //default 36
                arrayField = message.setFieldSize(FixConstants.messageLogon.length).getField();
                arrayGroup = message.setGroupSize(1).getGroup();
                for (short i = 0; i < arrayField.length(); i++) {
                    arrayField.get(i).setName(FixConstants.messageLogon[i]);
                    if (i == 0 || i == 1) {
                        arrayField.get(i).setRequired("Y");
                    } else {
                        arrayField.get(i).setRequired("N");
                    }
                }
                arrayGroup.get(0).setName(FixConstants.messageLogonGrp);
                arrayGroup.get(0).setRequired("N");
                arrayField = null;
                arrayField = arrayGroup.get(0).setFieldSize(
                        FixConstants.messageLogonGrpFlds.length).getField();
                for (short j = 0; j < arrayField.length(); j++) {
                    arrayField.get(0).setName(FixConstants.messageLogonGrpFlds[j]);
                    arrayField.get(0).setRequired("N");
                }
            case 11://News
                message.setMsgcat(FixConstants.messagesMsgCat[1]);
                arrayField = null;
                arrayGroup = null;
                arrayField = message.setFieldSize(FixConstants.messageNews.length).getField();
                arrayGroup = message.setGroupSize(FixConstants.messageNewsGrp.length).getGroup();
                for (short i = 0; i < arrayField.length(); i++) {
                    arrayField.get(i).setName(FixConstants.messageNews[i]);
                    if (i == 0) {
                        arrayField.get(i).setRequired("Y");
                    } else {
                        arrayField.get(i).setRequired("N");
                    }
                }
                for (short i = 0; i < arrayGroup.length(); i++) {
                    arrayGroup.get(i).setName(FixConstants.messageNewsGrp[i]);
                    arrayField = null;
                    if (i == 0) {
                        arrayGroup.get(i).setRequired("N");
                        arrayField = arrayGroup.get(i).setFieldSize(2).getField();
                        for (short j = 0; j < arrayField.length(); j++) {
                            arrayField.get(j).setName(FixConstants.messageNewsGrpFlds[j]);
                            arrayField.get(j).setRequired("N");
                        }
                    } else if (i == 1) {
                        arrayGroup.get(i).setRequired("N");
                        arrayField = arrayGroup.get(i).setFieldSize(19).getField();
                        for (short j = 0; j < arrayField.length(); j++) {
                            arrayField.get(j).setName(FixConstants.messageNewsGrpFlds[j + 2]);
                            arrayField.get(j).setRequired("N");
                        }
                    } else if (i == 2) {
                        arrayGroup.get(i).setRequired("Y");
                        arrayField = arrayGroup.get(i).setFieldSize(3).getField();
                        for (short j = 0; j < arrayField.length(); j++) {
                            arrayField.get(j).setName(FixConstants.messageNewsGrpFlds[j + 21]);
                            if (j == 0) {
                                arrayField.get(j).setRequired("Y");//LinesOfText
                            } else {
                                arrayField.get(j).setRequired("N");
                            }
                        }
                    }
                }
            case 12://Email
                message.setMsgcat(FixConstants.messagesMsgCat[1]);
                arrayField = null;
                arrayGroup = null;
                arrayField = message.setFieldSize(FixConstants.messagesEmail.length).getField();
                arrayGroup = message.setGroupSize(FixConstants.messagesEmaiGrp.length).getGroup();
                for (short i = 0; i < arrayField.length(); i++) {
                    arrayField.get(i).setName(FixConstants.messagesEmail[i]);
                    if (i == 0 || i == 1 || i == 3) {
                        arrayField.get(i).setRequired("Y");
                    } else {
                        arrayField.get(i).setRequired("N");
                    }
                }
                for (short i = 0; i < arrayGroup.length(); i++) {
                    arrayGroup.get(i).setName(FixConstants.messagesEmaiGrp[i]);
                    arrayField = null;
                    if (i == 0) {
                        arrayGroup.get(i).setRequired("N");
                        arrayField = arrayGroup.get(i).setFieldSize(2).getField();
                        for (short j = 0; j < arrayField.length(); j++) {
                            arrayField.get(j).setName(FixConstants.messagesEmaiGrpFlds[j]);
                            arrayField.get(j).setRequired("N");
                        }
                    } else if (i == 1) {
                        arrayGroup.get(i).setRequired("N");
                        arrayField = arrayGroup.get(i).setFieldSize(19).getField();
                        for (short j = 0; j < arrayField.length(); j++) {
                            arrayField.get(j).setName(FixConstants.messagesEmaiGrpFlds[j + 2]);
                            arrayField.get(j).setRequired("N");
                        }
                    } else if (i == 2) {
                        arrayGroup.get(i).setRequired("Y");
                        arrayField = arrayGroup.get(i).setFieldSize(3).getField();
                        for (short j = 0; j < arrayField.length(); j++) {
                            arrayField.get(j).setName(FixConstants.messagesEmaiGrpFlds[j + 21]);
                            if (j == 0) {
                                arrayField.get(j).setRequired("Y");//LinesOfText
                            } else {
                                arrayField.get(j).setRequired("N");
                            }
                        }
                    }
                }
            case 13://NewOrderSingle
                message.setMsgcat(FixConstants.messagesMsgCat[1]);
                arrayField = null;
                arrayGroup = null;
                arrayField = message.setFieldSize(FixConstants.messagesNOS.length).getField();
                arrayGroup = message.setGroupSize(FixConstants.messagesNOSGrp.length).getGroup();
                for (short i = 0; i < arrayField.length(); i++) {
                    arrayField.get(i).setName(FixConstants.messagesNOS[i]);
                    if (i == 0 || i == 6 || i == 12 || i == 32 || i == 34 || i == 37) {
                        arrayField.get(i).setRequired("Y");
                    } else {
                        arrayField.get(i).setRequired("N");
                    }
                }
                for (short i = 0; i < arrayGroup.length(); i++) {
                    arrayGroup.get(i).setName(FixConstants.messagesNOSGrp[i]);
                    arrayGroup.get(i).setRequired("N");
                    arrayField = null;
                    if (i == 0) {
                        arrayField = arrayGroup.get(i).setFieldSize(2).getField();
                        for (short j = 0; j < arrayField.length(); j++) {
                            arrayField.get(j).setName(FixConstants.messagesNOSGrpFlds[j]);
                            arrayField.get(j).setRequired("N");
                        }
                    } else if (i == 1) {
                        arrayField = arrayGroup.get(i).setFieldSize(1).getField();
                        for (short j = 0; j < arrayField.length(); j++) {
                            arrayField.get(j).setName(FixConstants.messagesNOSGrpFlds[j + 2]);
                            arrayField.get(j).setRequired("N");
                        }
                    }
                }
            case 14://NewOrderList
                message.setMsgcat(FixConstants.messagesMsgCat[1]);
                arrayField = null;
                arrayGroup = null;
                arrayField = message.setFieldSize(FixConstants.messagesNOL.length).getField();
                arrayGroup = message.setGroupSize(1).getGroup();
                for (short i = 0; i < arrayField.length(); i++) {
                    arrayField.get(i).setName(FixConstants.messagesNOL[i]);
                    if (i == 0 || i == 4 || i == 10) {
                        arrayField.get(i).setRequired("Y");
                    } else {
                        arrayField.get(i).setRequired("N");
                    }
                }
                arrayGroup.get(0).setName(FixConstants.messagesNOLOuterGrp);
                arrayGroup.get(0).setRequired("Y");
                arrayField = null;
                arrayField = arrayGroup.get(0).setFieldSize(
                        FixConstants.messagesNOLOuterGrpFlds.length).getField();
                for (short j = 0; j < arrayField.length(); j++) {
                    arrayField.get(j).setName(FixConstants.messagesNOLOuterGrpFlds[j]);
                    if (j == 0 || j == 1 || j == 14 || j == 34) {
                        arrayField.get(j).setRequired("Y");
                    } else {
                        arrayField.get(j).setRequired("N");
                    }
                }
                arrayInnerGroup = arrayGroup.get(0).setGroupSize(2).getGroup();
                for (short i = 0; i < arrayInnerGroup.length(); i++) {
                    arrayInnerGroup.get(i).setName(FixConstants.messagesNOLInnerGrp[i]);
                    arrayInnerGroup.get(i).setRequired("N");
                    arrayField = null;
                    if (i == 0) {
                        arrayField = arrayInnerGroup.get(i).setFieldSize(2).getField();
                        for (short j = 0; j < arrayField.length(); j++) {
                            arrayField.get(j).setName(FixConstants.messagesNOLInnerGrpFlds[j]);
                            arrayField.get(j).setRequired("N");
                        }
                    } else if (i == 1) {
                        arrayField = arrayInnerGroup.get(i).setFieldSize(1).getField();
                        for (short j = 0; j < arrayField.length(); j++) {
                            arrayField.get(j).setName(FixConstants.messagesNOLInnerGrpFlds[j + 2]);
                            arrayField.get(j).setRequired("N");
                        }
                    }
                }

            case 15://OrderCancelRequest
                message.setMsgcat(FixConstants.messagesMsgCat[1]);
                arrayField = null;
                arrayGroup = null;
                arrayField = message.setFieldSize(FixConstants.messagesOCR.length).getField();
                for (short i = 0; i < arrayField.length(); i++) {
                    arrayField.get(i).setName(FixConstants.messagesOCR[i]);
                    if (i == 0 || i == 2 || i == 7 || i == 27) {
                        arrayField.get(i).setRequired("Y");
                    } else {
                        arrayField.get(i).setRequired("N");
                    }
                }
            case 16://OrderCancelReplaceRequest
                message.setMsgcat(FixConstants.messagesMsgCat[1]);
                arrayField = null;
                arrayGroup = null;
                arrayField = message.setFieldSize(FixConstants.messagesOCR.length).getField();
                arrayGroup = message.setGroupSize(FixConstants.messagesOCRGrp.length).getGroup();
                for (short i = 0; i < arrayField.length(); i++) {
                    arrayField.get(i).setName(FixConstants.messagesOCR[i]);
                    if (i == 3 || i == 4 || i == 9 || i == 14 || i == 33 || i == 34 || i == 37) {
                        arrayField.get(i).setRequired("Y");
                    } else {
                        arrayField.get(i).setRequired("N");
                    }
                }
                for (short i = 0; i < arrayGroup.length(); i++) {
                    arrayGroup.get(i).setName(FixConstants.messagesOCRGrp[i]);
                    arrayGroup.get(i).setRequired("N");
                    arrayField = null;
                    if (i == 0) {
                        arrayField = arrayGroup.get(i).setFieldSize(2).getField();
                        for (short j = 0; j < arrayField.length(); j++) {
                            arrayField.get(j).setName(FixConstants.messagesOCRGrpFlds[j]);
                            arrayField.get(j).setRequired("N");
                        }
                    } else if (i == 1) {
                        arrayField = arrayGroup.get(i).setFieldSize(1).getField();
                        for (short j = 0; j < arrayField.length(); j++) {
                            arrayField.get(j).setName(FixConstants.messagesOCRGrpFlds[j + 2]);
                            arrayField.get(j).setRequired("N");
                        }
                    }
                }
            case 17://OrderStatusRekquest
                message.setMsgcat(FixConstants.messagesMsgCat[1]);
                arrayField = null;
                arrayGroup = null;
                arrayField = message.setFieldSize(FixConstants.messagesOSR.length).getField();
                for (short i = 0; i < arrayField.length(); i++) {
                    arrayField.get(i).setName(FixConstants.messagesOSR[i]);
                    if (i == 1 || i == 5 || i == 2 || i == 4) {
                        arrayField.get(i).setRequired("Y");
                    } else {
                        arrayField.get(i).setRequired("N");
                    }
                }
            case 18://Allocation
                message.setMsgcat(FixConstants.messagesMsgCat[1]);
                arrayField = null;
                arrayGroup = null;
                arrayField = message.setFieldSize(FixConstants.messagesAlloc.length).getField();
                arrayGroup = message.setGroupSize(FixConstants.messagesAllocOGrp.length).getGroup();
                for (short i = 0; i < arrayField.length(); i++) {
                    arrayField.get(i).setName(FixConstants.messagesAlloc[i]);
                    if (i == 0 || i == 1 || i == 5 || i == 6 || i == 25 || i == 28 || i == 31) {
                        arrayField.get(i).setRequired("Y");
                    } else {
                        arrayField.get(i).setRequired("N");
                    }
                }
                for (short i = 0; i < arrayGroup.length(); i++) {
                    arrayGroup.get(i).setName(FixConstants.messagesAllocOGrp[i]);
                    arrayGroup.get(i).setRequired("N");
                    arrayField = null;
                    arrayField = arrayGroup.get(i).setFieldSize(
                            FixConstants.messagesAllocOGrpFlds.length).getField();
                    for (short j = 0; j < arrayField.length(); j++) {
                        arrayField.get(j).setName(FixConstants.messagesAllocOGrpFlds[j]);
                        if (i == 2 && j == 2) {
                            arrayField.get(j).setRequired("Y");
                            arrayInnerGroup = arrayGroup.get(i).setGroupSize(1).getGroup();
                            arrayInnerGroup.get(0).setName(FixConstants.messagesAllocIGrp);
                            arrayInnerGroup.get(0).setRequired("N");
                            arrayField = null;
                            arrayField = arrayGroup.get(j).setFieldSize(
                                    FixConstants.messagesAllocIGrpFlds.length).getField();
                            for (short k = 0; k < arrayField.length(); k++) {
                                arrayField.get(k).setName(FixConstants.messagesAllocIGrpFlds[j]);
                                arrayField.get(k).setRequired("N");
                            }
                        } else {
                            arrayField.get(j).setRequired("N");
                        }
                    }
                }
            case 19://ListCancelRequest
                fieldYesArray = null;
                fieldArray = null;
                groupYesArray = null;
                groupArray = null;
                groupFieldSize = null;
                groupFieldYesArray = null;
                groupField = null;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], new int[]{0, 1},
                        FixConstants.messagesListCancelReq,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField,
                        innerGroupFieldSize, innerGroupField, innerGroup);
            case 20://ListExecute
                fieldYesArray = null;
                fieldArray = null;
                groupYesArray = null;
                groupArray = null;
                groupFieldSize = null;
                groupFieldYesArray = null;
                groupField = null;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], new int[]{0, 3},
                        FixConstants.messagesListExec,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField,
                        innerGroupFieldSize, innerGroupField, innerGroup);
            case 21://ListStatusRequest
                fieldYesArray = null;
                fieldArray = null;
                groupYesArray = null;
                groupArray = null;
                groupFieldSize = null;
                groupFieldYesArray = null;
                groupField = null;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], new int[]{0},
                        FixConstants.messagesListStatusReq,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField,
                        innerGroupFieldSize, innerGroupField, innerGroup);
            case 22://ListStatus
                fieldYesArray = new int[]{0, 1, 3, 4, 9};
                fieldArray = null;
                groupYesArray = new int[]{0};
                groupArray = FixConstants.messagesListStatusGrp;
                groupFieldSize = new int[]{FixConstants.messagesListStatusGrpFld.length};
                groupFieldYesArray = new int[][]{new int[]{0, 1, 2, 3, 4, 5}};
                groupField = FixConstants.messagesListStatusGrpFld;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesListStatusReq,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 23:
                fieldYesArray = new int[]{2, 3, 5};
                fieldArray = null;
                groupYesArray = null;
                groupArray = null;
                groupFieldSize = null;
                groupFieldYesArray = null;
                groupField = null;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesAllocationAck,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 24://DontKnowTrade
                fieldYesArray = new int[]{0, 1, 2, 3, 22};
                fieldArray = null;
                groupYesArray = null;
                groupArray = null;
                groupFieldSize = null;
                groupFieldYesArray = null;
                groupField = null;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesDNT,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 25://QuoteRequest
                fieldYesArray = new int[]{0};
                fieldArray = null;
                groupYesArray = new int[]{0};
                groupArray = FixConstants.messagesQuoteReqGrp;
                groupFieldSize = new int[]{FixConstants.messagesQuoteReqGrpFld.length};
                groupFieldYesArray = new int[][]{new int[]{0}};
                groupField = FixConstants.messagesQuoteReqGrpFld;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesQuoteReq,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 26://Quote
                fieldYesArray = new int[]{1, 4};
                fieldArray = null;
                groupYesArray = null;
                groupArray = null;
                groupFieldSize = null;
                groupFieldYesArray = null;
                groupField = null;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesQuote,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 27://SettlementInstructions
                fieldYesArray = new int[]{0, 1, 2, 3, 4, 5, 14};
                fieldArray = null;
                groupYesArray = null;
                groupArray = null;
                groupFieldSize = null;
                groupFieldYesArray = null;
                groupField = null;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messageSettlementInst,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 28://MarketDataRequest
                fieldYesArray = new int[]{0, 1, 2};
                fieldArray = null;
                groupYesArray = new int[]{0, 1};
                groupArray = FixConstants.messagesMarketDataReqGrp;
                groupFieldSize = new int[]{FixConstants.messagesMarketDataReqGrpFld.length};
                groupFieldYesArray = new int[][]{new int[]{0}, new int[]{0}};
                groupField = FixConstants.messagesMarketDataReqGrpFld;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesMarketDataReq,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 29://MarketDataSnapshotFullRefresh
                fieldYesArray = new int[]{1};
                fieldArray = null;
                groupYesArray = new int[]{0};
                groupArray = FixConstants.messagesMarketDataSSFReqGrp;
                groupFieldSize = new int[]{FixConstants.messagesMarketDataSSFReqGrpFld.length};
                groupFieldYesArray = new int[][]{new int[]{0, 1}};
                groupField = FixConstants.messagesMarketDataSSFReqGrpFld;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesMarketDataSSFReq,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 30://MarketDataIncrementalRefresh
                fieldYesArray = null;
                fieldArray = null;
                groupYesArray = new int[]{0};
                groupArray = FixConstants.messagesMarketDataINCRReqGrp;
                groupFieldSize = new int[]{FixConstants.messagesMarketDataINCRReqGrpFld.length};
                groupFieldYesArray = new int[][]{new int[]{0}};
                groupField = FixConstants.messagesMarketDataINCRReqGrpFld;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesMarketDataINCRReq,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 31://MarketDataRequestReject
                fieldYesArray = new int[]{0};
                fieldArray = null;
                groupYesArray = null;
                groupArray = null;
                groupFieldSize = null;
                groupFieldYesArray = null;
                groupField = null;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesMarketDataReqRej,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 32://QuoteCancel
                fieldYesArray = new int[]{1, 2};
                ;
                fieldArray = null;
                groupYesArray = new int[]{0};
                groupArray = FixConstants.messagesQuoteCancelGrp;
                groupFieldSize = new int[]{FixConstants.messagesQuoteCancelGrpFlds.length};
                groupFieldYesArray = new int[][]{new int[]{0}};
                groupField = FixConstants.messagesQuoteCancelGrpFlds;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesQuoteCancel,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 33://QuoteStatusRequest
                fieldYesArray = new int[]{1};
                fieldArray = null;
                groupYesArray = null;
                groupArray = null;
                groupFieldSize = null;
                groupFieldYesArray = null;
                groupField = null;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesQuoteStmtReq,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 34://QuoteAcknowledgement
                fieldYesArray = new int[]{2};
                fieldArray = null;
                groupYesArray = null;
                groupArray = FixConstants.messagesQuoteAckGrp;
                groupFieldSize = new int[]{FixConstants.messagesQuoteAckGrpFlds.length};
                groupFieldYesArray = null;
                groupField = FixConstants.messagesQuoteAckGrpFlds;
                innerGroupFieldSize = null;
                innerGroupField = FixConstants.messagesIQuoteAckGrpFlds;
                innerGroup = FixConstants.messagesIQuoteAckGrp;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesQuoteAck,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 35://SecurityDefinitionRequest
                fieldYesArray = new int[]{2};
                fieldArray = null;
                groupYesArray = null;
                groupArray = FixConstants.messagesSecDefReqGrp;
                groupFieldSize = new int[]{FixConstants.messagesSecDefReqGrpFlds.length};
                groupFieldYesArray = null;
                groupField = FixConstants.messagesSecDefReqGrpFlds;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesSecDefReq,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 36://SecurityDefinition
                fieldYesArray = new int[]{0, 1, 3};
                fieldArray = null;
                groupYesArray = null;
                groupArray = FixConstants.messagesSecDefGrp;
                groupFieldSize = new int[]{FixConstants.messagesSecDefGrpFlds.length};
                groupFieldYesArray = null;
                groupField = FixConstants.messagesSecDefGrpFlds;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesSecDef,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 37://SecurityStatusRequest
                fieldYesArray = new int[]{0, 1, 3};
                fieldArray = null;
                groupYesArray = null;
                groupArray = null;
                groupFieldSize = null;
                groupFieldYesArray = null;
                groupField = null;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesSecStatusReq,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 38://SecurityStatus
                fieldYesArray = new int[]{1};
                fieldArray = null;
                groupYesArray = null;
                groupArray = null;
                groupFieldSize = null;
                groupFieldYesArray = null;
                groupField = null;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesSecStatus,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 39://TradingSessionStatusRequest
                fieldYesArray = new int[]{0, 4};
                fieldArray = null;
                groupYesArray = null;
                groupArray = null;
                groupFieldSize = null;
                groupFieldYesArray = null;
                groupField = null;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesTSSReq,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 40://TradingSessionStatus
                fieldYesArray = new int[]{1, 5};
                fieldArray = null;
                groupYesArray = null;
                groupArray = null;
                groupFieldSize = null;
                groupFieldYesArray = null;
                groupField = null;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesTSS,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 41://MassQuote (InnerGroup TODO)
                fieldYesArray = new int[]{1};
                fieldArray = null;
                groupYesArray = new int[]{0};
                groupArray = FixConstants.messagesMassQGrp;
                groupFieldSize = new int[]{FixConstants.messagesMassQGrpFlds.length};
                groupFieldYesArray = null;
                groupField = FixConstants.messagesMassQGrpFlds;
                innerGroupFieldSize = null;
                innerGroupField = FixConstants.messagesIMassQGrpFlds;
                innerGroup = FixConstants.messagesIMassQGrp;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesMassQ,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 42://BusinessMessageReject
                fieldYesArray = new int[]{1, 3};
                fieldArray = null;
                groupYesArray = null;
                groupArray = null;
                groupFieldSize = null;
                groupFieldYesArray = null;
                groupField = null;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesBMRej,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 43://BidRequest
                fieldYesArray = new int[]{1, 2, 4, 21, 22};
                fieldArray = null;
                groupYesArray = null;
                groupArray = FixConstants.messagesBidReqGrp;
                groupFieldSize = new int[]{FixConstants.messagesBidReqGrpFld.length};
                groupFieldYesArray = null;
                groupField = FixConstants.messagesBidReqGrpFld;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesBidReq,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 44://BidResponse
                fieldYesArray = null;
                fieldArray = null;
                groupYesArray = new int[]{0, 1};
                groupArray = FixConstants.messagesBidResGrp;
                groupFieldSize = new int[]{FixConstants.messagesBidResGrpFld.length};
                groupFieldYesArray = null;
                groupField = FixConstants.messagesBidResGrpFld;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesBidRes,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
            case 45://ListStrikePrice
                fieldYesArray = null;
                fieldArray = null;
                groupYesArray = new int[]{0, 22};
                groupArray = FixConstants.messagesLSPriceGrp;
                groupFieldSize = new int[]{FixConstants.messagesLSPriceGrpFld.length};
                groupFieldYesArray = null;
                groupField = FixConstants.messagesLSPriceGrpFld;
                innerGroupFieldSize = null;
                innerGroupField = null;
                innerGroup = null;
                addMessageInfo(message, FixConstants.messagesMsgCat[1], fieldYesArray, FixConstants.messagesLSPrice,
                        groupYesArray, groupArray, groupFieldSize, groupFieldYesArray, groupField, innerGroupFieldSize, innerGroupField
                        , innerGroup);
        }

    }

    private void addMessageInfo(
            Message message, String messageCat,
            int[] fieldYesArray, String[] fieldArray,
            int[] groupYesArray, String[] groupArray, int[] groupFieldSize, int[][] groupFieldYesArray, String[] groupField,
            int[] innerGroupFieldSize, String[] innerGroupField, String[] innerGroup
    ) {

        HugeArray<Field> arrayField = null;
        HugeArray<Group> arrayGroup = null;

        message.setMsgcat(messageCat);
        arrayField = message.setFieldSize(fieldArray.length).getField();
        for (short i = 0; i < arrayField.length(); i++) {
            arrayField.get(i).setName(fieldArray[i]);
            for (short j = 0; j < fieldYesArray.length; j++) {
                if (i == j) {
                    arrayField.get(i).setRequired("Y");
                    break;
                } else {
                    arrayField.get(i).setRequired("N");
                }
            }
        }

        if (groupArray != null) {
            arrayGroup = message.setGroupSize(groupArray.length).getGroup();
            for (short i = 0; i < arrayGroup.length(); i++) {
                arrayGroup.get(i).setName(groupArray[i]);
                for (short j = 0; j < groupYesArray.length; j++) {
                    if (i == j) {
                        arrayGroup.get(i).setRequired("Y");
                        break;
                    } else {
                        arrayGroup.get(i).setRequired("N");
                    }
                }
                arrayField = null;
                short groupFieldCounter = 0;
                int[] arrayYesCounter = groupFieldYesArray[i];
                arrayField = arrayGroup.get(i).setFieldSize(groupFieldSize[i]).getField();

                for (short j = groupFieldCounter; j < arrayField.length(); j++) {
                    arrayField.get(j).setName(groupField[j]);
                    for (short k = 0; j < arrayYesCounter.length; k++) {
                        if (j == k) {
                            arrayField.get(j).setRequired("Y");
                            break;
                        } else {
                            arrayField.get(j).setRequired("N");
                        }
                    }
                    arrayField.get(j).setRequired("N");
                    groupFieldCounter++;
                }

                addGroupToGroup(arrayGroup.get(i), innerGroupFieldSize, innerGroupField, innerGroup);
            }
        }
    }

    private void addGroupToGroup(Group group,
                                 int[] innerGroupFieldSize, String[] innerGroupField, String[] innerGroup
    ) {
        HugeArray<Group> arrayInnerGroup = null;
        HugeArray<Field> arrayField = null;
        if (innerGroupFieldSize == null) {
            return;
        }

        arrayInnerGroup = group.setGroupSize(innerGroup.length).getGroup();
        for (short i = 0; i < innerGroup.length; i++) {
            arrayInnerGroup.get(i).setName(innerGroup[i]);
            arrayInnerGroup.get(i).setRequired("N");
            arrayField = null;
            arrayField = group.setFieldSize(innerGroupField.length).getField();
            for (short k = 0; k < arrayField.length(); k++) {
                arrayField.get(k).setName(innerGroupField[k]);
                arrayField.get(k).setRequired("N");
            }
        }


    }

    /**
     * Static factory method to initializes Trailer fields of FIX 4.2 version
     *
     * @return - FixConfig
     */
    public FixConfig createServerFixTrailer() {
        if ((currentFixVersion & fix4_2_0_mask) != 0) {
            load42DefaultTrailer();
        }
        return this;
    }

    private void load42DefaultTrailer() {
        //this.trailer = dvg.nativeInstance(Trailer.class);
        this.trailer = new Trailer();
        HugeArray<Field> array = this.trailer.setFieldSize(3).getField();
        Field field = null;
        for (short i = 0; i < FixConstants.trailerFieldName.length; i++) {
            field = array.get(i);
            field.setName(FixConstants.trailerFieldName[i]);
            if (i == 2) {
                field.setRequired("Y");
            } else {
                field.setRequired("N");
            }
        }
    }


    @SuppressWarnings("unused")
    private FixConfig createServerFixComponents() {
        return this;
    }

    /**
     * Static factory method to initializes all the standard FIX &lt;Field&gt; of FIX 4.2 version
     *
     * @return -FixConfig
     */
    public FixConfig createServerFixFields() {
        if ((currentFixVersion & fix4_2_0_mask) != 0) {
            load42DefaultFields();
        }
        return this;
    }

    private Field[] fieldArr;


    /**
     * Returns an array of all the initialized Fix Fields
     *
     * @return -Field[]
     */
    public Field[] getFieldArr() {
        return fieldArr;
    }

    private void load42DefaultFields() {
        //this.fields = dvg.nativeInstance(Fields.class);
        //this.fields = new Fields();
        //HugeArray<Field> array =this.header.setFieldSize(FixConstants.fieldsNumber.length).getField();
        fieldArr = new Field[FixConstants.fieldsNumber.length];
        //Arrays.fill(fieldArr, 0,fieldArr.length,fieldFill);
        //initializing Field for this Fields
        //Field field = null;
        for (short i = 0; i < FixConstants.fieldsNumber.length; i++) {
            //field = array.get(i);
            //field = fieldArr[i];
            fieldArr[i] = new Field();
            fieldArr[i].setNumber(FixConstants.fieldsNumber[i]);
            fieldArr[i].setName(FixConstants.fieldsName[i]);
            fieldArr[i].setType(FieldLookup.fieldFor(FixConstants.fieldsTypeOrdering[i]));
        }
        //Generating Empty Values Fields (including defined ones assuming SINGLE)
		/*HugeArray<Value> valueArr = null;
		Value value = null;
		for (short i=0;i<FixConstants.fieldsNumber.length;i++){
			field = array.get(i);
			//valueArr = field.setValueSize(1).getValue();//will change it if #ofValues > 1 on a field
			value = valueArr.get(i);value.setEnum("");//TODO
		}	*/
    }

    /**
     * Returns Fix version 'Major'; like 3 or 4 or 5
     *
     * @return - fixVersionMajor
     */
    public int getFixVersionMajor() {
        return fixVersionMajor;
    }


    /**
     * Static factory method to set FIX protocol version-major
     *
     * @param fixVersionMajor
     * @return - FixConfig
     */
    public FixConfig setFixVersionMajor(int fixVersionMajor) {
        this.fixVersionMajor = fixVersionMajor;
        return this;
    }

    /**
     * Returns minor value of the FIX protocol version like 2 or 4 for Fix 4.2 or 4.4
     *
     * @return -fixVersionMinor
     */
    public int getFixVersionMinor() {
        return fixVersionMinor;
    }

    /**
     * Static factory method to set FIX protocol version-minor
     *
     * @param fixVersionMinor
     * @return -FixConfig
     */
    public FixConfig setFixVersionMinor(int fixVersionMinor) {
        this.fixVersionMinor = fixVersionMinor;
        return this;
    }

    /**
     * Returns Fix Protocol service Pack value for this FIX object
     *
     * @return -fixVersionServicePack
     */
    public int getFixVersionServicePack() {
        return fixVersionServicePack;
    }

    /**
     * Static factory method to set Fix Protocol service Pack value
     *
     * @return -FixConfig
     */
    public FixConfig setFixVersionServicePack(int fixVersionServicePack) {
        this.fixVersionServicePack = fixVersionServicePack;
        setCurrentFixVersion();
        return this;
    }

    private void setCurrentFixVersion() {
        currentFixVersion = fixVersionMajor | fixVersionMinor | fixVersionServicePack;
    }

    /**
     * Returns Tailer object for this FIX message
     *
     * @return -trailer
     */
    public Trailer getTrailer() {
        return trailer;
    }

    /**
     * Returns Header object for this FIX message
     *
     * @return -header
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Returns Messages object for this FIX message
     *
     * @return -messages
     */
    public Messages getMessages() {
        return messages;
    }

    /**
     * Returns Components object for this FIX message
     *
     * @return -components
     */
    public Components getComponents() {
        return components;
    }

    /**
     * Returns Fields object for this FIX message
     *
     * @return -fields
     */
    public Fields getFields() {
        return fields;
    }
}
