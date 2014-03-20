package com.gcj.dzh.common;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;

import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessage.Builder;
import com.google.protobuf.GeneratedMessage.FieldAccessorTable;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilder;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PCMsgProto {
	private static Descriptors.Descriptor internal_static_pbmsg_PCUrl_descriptor;
	private static GeneratedMessage.FieldAccessorTable internal_static_pbmsg_PCUrl_fieldAccessorTable;
	private static Descriptors.Descriptor internal_static_pbmsg_PCUrls_descriptor;
	private static GeneratedMessage.FieldAccessorTable internal_static_pbmsg_PCUrls_fieldAccessorTable;
	private static Descriptors.Descriptor internal_static_pbmsg_PCItem_descriptor;
	private static GeneratedMessage.FieldAccessorTable internal_static_pbmsg_PCItem_fieldAccessorTable;
	private static Descriptors.FileDescriptor descriptor;

	static {
		String[] descriptorData = { "\n\013pcmsg.proto\022\005pbmsg\"8\n\005PCUrl\022\017\n\007oprTime\030\001 \002(\003\022\021\n\tfuncionId\030\002 \002(\005\022\013\n\003url\030\003 \002(\t\"%\n\006PCUrls\022\033\n\005pcUrl\030\001 \003(\0132\f.pbmsg.PCUrl\"r\n\006PCItem\022\n\n\002ip\030\001 \002(\t\022\013\n\003pid\030\002 \002(\005\022\f\n\004user\030\003 \002(\t\022\017\n\007version\030\004 \002(\t\022\021\n\tproductId\030\005 \002(\005\022\016\n\006aesmsg\030\006 \002(\f\022\r\n\005msgId\030\007 \001(\005B!\n\023com.gw.common.pbmsgB\nPCMsgProto" };

		Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
			public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
				PCMsgProto.descriptor = root;
				PCMsgProto.internal_static_pbmsg_PCUrl_descriptor = (Descriptors.Descriptor) PCMsgProto.getDescriptor().getMessageTypes().get(0);
				PCMsgProto.internal_static_pbmsg_PCUrl_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(PCMsgProto.internal_static_pbmsg_PCUrl_descriptor, new String[] { "OprTime", "FuncionId", "Url" });
				PCMsgProto.internal_static_pbmsg_PCUrls_descriptor = (Descriptors.Descriptor) PCMsgProto.getDescriptor().getMessageTypes().get(1);
				PCMsgProto.internal_static_pbmsg_PCUrls_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(PCMsgProto.internal_static_pbmsg_PCUrls_descriptor, new String[] { "PcUrl" });
				PCMsgProto.internal_static_pbmsg_PCItem_descriptor = (Descriptors.Descriptor) PCMsgProto.getDescriptor().getMessageTypes().get(2);
				PCMsgProto.internal_static_pbmsg_PCItem_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(PCMsgProto.internal_static_pbmsg_PCItem_descriptor, new String[] { "Ip", "Pid", "User", "Version", "ProductId", "Aesmsg", "MsgId" });
				return null;
			}
		};
		Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0], assigner);
	}

	public static void registerAllExtensions(ExtensionRegistry registry) {
	}

	public static Descriptors.FileDescriptor getDescriptor() {
		return descriptor;
	}

	public static final class PCItem extends GeneratedMessage implements PCMsgProto.PCItemOrBuilder {
		private static final PCItem defaultInstance;
		private final UnknownFieldSet unknownFields;
		public static Parser<PCItem> PARSER = new AbstractParser() {
			public PCMsgProto.PCItem parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
				return new PCMsgProto.PCItem(input, extensionRegistry);
			}
		};
		private int bitField0_;
		public static final int IP_FIELD_NUMBER = 1;
		private Object ip_;
		public static final int PID_FIELD_NUMBER = 2;
		private int pid_;
		public static final int USER_FIELD_NUMBER = 3;
		private Object user_;
		public static final int VERSION_FIELD_NUMBER = 4;
		private Object version_;
		public static final int PRODUCTID_FIELD_NUMBER = 5;
		private int productId_;
		public static final int AESMSG_FIELD_NUMBER = 6;
		private ByteString aesmsg_;
		public static final int MSGID_FIELD_NUMBER = 7;
		private int msgId_;
		private byte memoizedIsInitialized = -1;

		private int memoizedSerializedSize = -1;
		private static final long serialVersionUID = 0L;

		static {
			defaultInstance = new PCItem(true);
			defaultInstance.initFields();
		}

		private PCItem(GeneratedMessage.Builder<?> builder) {
			super();
			this.unknownFields = builder.getUnknownFields();
		}

		private PCItem(boolean noInit) {
			this.unknownFields = UnknownFieldSet.getDefaultInstance();
		}

		public static PCItem getDefaultInstance() {
			return defaultInstance;
		}

		public PCItem getDefaultInstanceForType() {
			return defaultInstance;
		}

		public final UnknownFieldSet getUnknownFields() {
			return this.unknownFields;
		}

		private PCItem(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
			initFields();
			int mutable_bitField0_ = 0;
			UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
			try {
				boolean done = false;
				while (!done) {
					int tag = input.readTag();
					switch (tag) {
					case 0:
						done = true;
						break;
					default:
						if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
							done = true;
						}
						break;
					case 10:
						this.bitField0_ |= 1;
						this.ip_ = input.readBytes();
						break;
					case 16:
						this.bitField0_ |= 2;
						this.pid_ = input.readInt32();
						break;
					case 26:
						this.bitField0_ |= 4;
						this.user_ = input.readBytes();
						break;
					case 34:
						this.bitField0_ |= 8;
						this.version_ = input.readBytes();
						break;
					case 40:
						this.bitField0_ |= 16;
						this.productId_ = input.readInt32();
						break;
					case 50:
						this.bitField0_ |= 32;
						this.aesmsg_ = input.readBytes();
						break;
					case 56:
						this.bitField0_ |= 64;
						this.msgId_ = input.readInt32();
					}
				}
			} catch (InvalidProtocolBufferException e) {
				throw e.setUnfinishedMessage(this);
			} catch (IOException e) {
				throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
			} finally {
				this.unknownFields = unknownFields.build();
				makeExtensionsImmutable();
			}
		}

		public static final Descriptors.Descriptor getDescriptor() {
			return PCMsgProto.internal_static_pbmsg_PCItem_descriptor;
		}

		protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
			return PCMsgProto.internal_static_pbmsg_PCItem_fieldAccessorTable.ensureFieldAccessorsInitialized(PCItem.class, Builder.class);
		}

		public Parser<PCItem> getParserForType() {
			return PARSER;
		}

		public boolean hasIp() {
			return (this.bitField0_ & 0x1) == 1;
		}

		public String getIp() {
			Object ref = this.ip_;
			if ((ref instanceof String)) {
				return (String) ref;
			}
			ByteString bs = (ByteString) ref;
			String s = bs.toStringUtf8();
			if (bs.isValidUtf8()) {
				this.ip_ = s;
			}
			return s;
		}

		public ByteString getIpBytes() {
			Object ref = this.ip_;
			if ((ref instanceof String)) {
				ByteString b = ByteString.copyFromUtf8((String) ref);
				this.ip_ = b;
				return b;
			}
			return (ByteString) ref;
		}

		public boolean hasPid() {
			return (this.bitField0_ & 0x2) == 2;
		}

		public int getPid() {
			return this.pid_;
		}

		public boolean hasUser() {
			return (this.bitField0_ & 0x4) == 4;
		}

		public String getUser() {
			Object ref = this.user_;
			if ((ref instanceof String)) {
				return (String) ref;
			}
			ByteString bs = (ByteString) ref;
			String s = bs.toStringUtf8();
			if (bs.isValidUtf8()) {
				this.user_ = s;
			}
			return s;
		}

		public ByteString getUserBytes() {
			Object ref = this.user_;
			if ((ref instanceof String)) {
				ByteString b = ByteString.copyFromUtf8((String) ref);
				this.user_ = b;
				return b;
			}
			return (ByteString) ref;
		}

		public boolean hasVersion() {
			return (this.bitField0_ & 0x8) == 8;
		}

		public String getVersion() {
			Object ref = this.version_;
			if ((ref instanceof String)) {
				return (String) ref;
			}
			ByteString bs = (ByteString) ref;
			String s = bs.toStringUtf8();
			if (bs.isValidUtf8()) {
				this.version_ = s;
			}
			return s;
		}

		public ByteString getVersionBytes() {
			Object ref = this.version_;
			if ((ref instanceof String)) {
				ByteString b = ByteString.copyFromUtf8((String) ref);
				this.version_ = b;
				return b;
			}
			return (ByteString) ref;
		}

		public boolean hasProductId() {
			return (this.bitField0_ & 0x10) == 16;
		}

		public int getProductId() {
			return this.productId_;
		}

		public boolean hasAesmsg() {
			return (this.bitField0_ & 0x20) == 32;
		}

		public ByteString getAesmsg() {
			return this.aesmsg_;
		}

		public boolean hasMsgId() {
			return (this.bitField0_ & 0x40) == 64;
		}

		public int getMsgId() {
			return this.msgId_;
		}

		private void initFields() {
			this.ip_ = "";
			this.pid_ = 0;
			this.user_ = "";
			this.version_ = "";
			this.productId_ = 0;
			this.aesmsg_ = ByteString.EMPTY;
			this.msgId_ = 0;
		}

		public final boolean isInitialized() {
			byte isInitialized = this.memoizedIsInitialized;
			if (isInitialized != -1)
				return isInitialized == 1;

			if (!hasIp()) {
				this.memoizedIsInitialized = 0;
				return false;
			}
			if (!hasPid()) {
				this.memoizedIsInitialized = 0;
				return false;
			}
			if (!hasUser()) {
				this.memoizedIsInitialized = 0;
				return false;
			}
			if (!hasVersion()) {
				this.memoizedIsInitialized = 0;
				return false;
			}
			if (!hasProductId()) {
				this.memoizedIsInitialized = 0;
				return false;
			}
			if (!hasAesmsg()) {
				this.memoizedIsInitialized = 0;
				return false;
			}
			this.memoizedIsInitialized = 1;
			return true;
		}

		public void writeTo(CodedOutputStream output) throws IOException {
			getSerializedSize();
			if ((this.bitField0_ & 0x1) == 1) {
				output.writeBytes(1, getIpBytes());
			}
			if ((this.bitField0_ & 0x2) == 2) {
				output.writeInt32(2, this.pid_);
			}
			if ((this.bitField0_ & 0x4) == 4) {
				output.writeBytes(3, getUserBytes());
			}
			if ((this.bitField0_ & 0x8) == 8) {
				output.writeBytes(4, getVersionBytes());
			}
			if ((this.bitField0_ & 0x10) == 16) {
				output.writeInt32(5, this.productId_);
			}
			if ((this.bitField0_ & 0x20) == 32) {
				output.writeBytes(6, this.aesmsg_);
			}
			if ((this.bitField0_ & 0x40) == 64) {
				output.writeInt32(7, this.msgId_);
			}
			getUnknownFields().writeTo(output);
		}

		public int getSerializedSize() {
			int size = this.memoizedSerializedSize;
			if (size != -1)
				return size;

			size = 0;
			if ((this.bitField0_ & 0x1) == 1) {
				size = size + CodedOutputStream.computeBytesSize(1, getIpBytes());
			}
			if ((this.bitField0_ & 0x2) == 2) {
				size = size + CodedOutputStream.computeInt32Size(2, this.pid_);
			}
			if ((this.bitField0_ & 0x4) == 4) {
				size = size + CodedOutputStream.computeBytesSize(3, getUserBytes());
			}
			if ((this.bitField0_ & 0x8) == 8) {
				size = size + CodedOutputStream.computeBytesSize(4, getVersionBytes());
			}
			if ((this.bitField0_ & 0x10) == 16) {
				size = size + CodedOutputStream.computeInt32Size(5, this.productId_);
			}
			if ((this.bitField0_ & 0x20) == 32) {
				size = size + CodedOutputStream.computeBytesSize(6, this.aesmsg_);
			}
			if ((this.bitField0_ & 0x40) == 64) {
				size = size + CodedOutputStream.computeInt32Size(7, this.msgId_);
			}
			size += getUnknownFields().getSerializedSize();
			this.memoizedSerializedSize = size;
			return size;
		}

		protected Object writeReplace() throws ObjectStreamException {
			return super.writeReplace();
		}

		public static PCItem parseFrom(ByteString data) throws InvalidProtocolBufferException {
			return (PCItem) PARSER.parseFrom(data);
		}

		public static PCItem parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
			return (PCItem) PARSER.parseFrom(data, extensionRegistry);
		}

		public static PCItem parseFrom(byte[] data) throws InvalidProtocolBufferException {
			return (PCItem) PARSER.parseFrom(data);
		}

		public static PCItem parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
			return (PCItem) PARSER.parseFrom(data, extensionRegistry);
		}

		public static PCItem parseFrom(InputStream input) throws IOException {
			return (PCItem) PARSER.parseFrom(input);
		}

		public static PCItem parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
			return (PCItem) PARSER.parseFrom(input, extensionRegistry);
		}

		public static PCItem parseDelimitedFrom(InputStream input) throws IOException {
			return (PCItem) PARSER.parseDelimitedFrom(input);
		}

		public static PCItem parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
			return (PCItem) PARSER.parseDelimitedFrom(input, extensionRegistry);
		}

		public static PCItem parseFrom(CodedInputStream input) throws IOException {
			return (PCItem) PARSER.parseFrom(input);
		}

		public static PCItem parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
			return (PCItem) PARSER.parseFrom(input, extensionRegistry);
		}

		public static Builder newBuilder() {
			return Builder.create();
		}

		public Builder newBuilderForType() {
			return newBuilder();
		}

		public static Builder newBuilder(PCItem prototype) {
			return newBuilder().mergeFrom(prototype);
		}

		public Builder toBuilder() {
			return newBuilder(this);
		}

		protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
			Builder builder = new Builder(parent);
			return builder;
		}

		public static final class Builder extends GeneratedMessage.Builder<Builder> implements PCMsgProto.PCItemOrBuilder {
			private int bitField0_;
			private Object ip_ = "";
			private int pid_;
			private Object user_ = "";

			private Object version_ = "";
			private int productId_;
			private ByteString aesmsg_ = ByteString.EMPTY;
			private int msgId_;

			public static final Descriptors.Descriptor getDescriptor() {
				return PCMsgProto.internal_static_pbmsg_PCItem_descriptor;
			}

			protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
				return PCMsgProto.internal_static_pbmsg_PCItem_fieldAccessorTable.ensureFieldAccessorsInitialized(PCMsgProto.PCItem.class, Builder.class);
			}

			private Builder() {
				maybeForceBuilderInitialization();
			}

			private Builder(GeneratedMessage.BuilderParent parent) {
				super();
				maybeForceBuilderInitialization();
			}

			private void maybeForceBuilderInitialization() {
				PCMsgProto.PCItem.newBuilder();
			}

			private static Builder create() {
				return new Builder();
			}

			public Builder clear() {
				super.clear();
				this.ip_ = "";
				this.bitField0_ &= -2;
				this.pid_ = 0;
				this.bitField0_ &= -3;
				this.user_ = "";
				this.bitField0_ &= -5;
				this.version_ = "";
				this.bitField0_ &= -9;
				this.productId_ = 0;
				this.bitField0_ &= -17;
				this.aesmsg_ = ByteString.EMPTY;
				this.bitField0_ &= -33;
				this.msgId_ = 0;
				this.bitField0_ &= -65;
				return this;
			}

			public Builder clone() {
				return create().mergeFrom(buildPartial());
			}

			public Descriptors.Descriptor getDescriptorForType() {
				return PCMsgProto.internal_static_pbmsg_PCItem_descriptor;
			}

			public PCMsgProto.PCItem getDefaultInstanceForType() {
				return PCMsgProto.PCItem.getDefaultInstance();
			}

			public PCMsgProto.PCItem build() {
				PCMsgProto.PCItem result = buildPartial();
				if (!result.isInitialized()) {
					throw newUninitializedMessageException(result);
				}
				return result;
			}

			public PCMsgProto.PCItem buildPartial() {
				PCMsgProto.PCItem result = new PCMsgProto.PCItem(this);
				int from_bitField0_ = this.bitField0_;
				int to_bitField0_ = 0;
				if ((from_bitField0_ & 0x1) == 1) {
					to_bitField0_ |= 1;
				}
				result.ip_ = this.ip_;
				if ((from_bitField0_ & 0x2) == 2) {
					to_bitField0_ |= 2;
				}
				result.pid_ = this.pid_;
				if ((from_bitField0_ & 0x4) == 4) {
					to_bitField0_ |= 4;
				}
				result.user_ = this.user_;
				if ((from_bitField0_ & 0x8) == 8) {
					to_bitField0_ |= 8;
				}
				result.version_ = this.version_;
				if ((from_bitField0_ & 0x10) == 16) {
					to_bitField0_ |= 16;
				}
				result.productId_ = this.productId_;
				if ((from_bitField0_ & 0x20) == 32) {
					to_bitField0_ |= 32;
				}
				result.aesmsg_ = this.aesmsg_;
				if ((from_bitField0_ & 0x40) == 64) {
					to_bitField0_ |= 64;
				}
				result.msgId_ = this.msgId_;
				result.bitField0_ = to_bitField0_;
				onBuilt();
				return result;
			}

			public Builder mergeFrom(Message other) {
				if ((other instanceof PCMsgProto.PCItem)) {
					return mergeFrom((PCMsgProto.PCItem) other);
				}
				super.mergeFrom(other);
				return this;
			}

			public Builder mergeFrom(PCMsgProto.PCItem other) {
				if (other == PCMsgProto.PCItem.getDefaultInstance())
					return this;
				if (other.hasIp()) {
					this.bitField0_ |= 1;
					this.ip_ = other.ip_;
					onChanged();
				}
				if (other.hasPid()) {
					setPid(other.getPid());
				}
				if (other.hasUser()) {
					this.bitField0_ |= 4;
					this.user_ = other.user_;
					onChanged();
				}
				if (other.hasVersion()) {
					this.bitField0_ |= 8;
					this.version_ = other.version_;
					onChanged();
				}
				if (other.hasProductId()) {
					setProductId(other.getProductId());
				}
				if (other.hasAesmsg()) {
					setAesmsg(other.getAesmsg());
				}
				if (other.hasMsgId()) {
					setMsgId(other.getMsgId());
				}
				mergeUnknownFields(other.getUnknownFields());
				return this;
			}

			public final boolean isInitialized() {
				if (!hasIp()) {
					return false;
				}
				if (!hasPid()) {
					return false;
				}
				if (!hasUser()) {
					return false;
				}
				if (!hasVersion()) {
					return false;
				}
				if (!hasProductId()) {
					return false;
				}
				if (!hasAesmsg()) {
					return false;
				}
				return true;
			}

			public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
				PCMsgProto.PCItem parsedMessage = null;
				try {
					parsedMessage = (PCMsgProto.PCItem) PCMsgProto.PCItem.PARSER.parsePartialFrom(input, extensionRegistry);
				} catch (InvalidProtocolBufferException e) {
					parsedMessage = (PCMsgProto.PCItem) e.getUnfinishedMessage();
					throw e;
				} finally {
					if (parsedMessage != null) {
						mergeFrom(parsedMessage);
					}
				}
				return this;
			}

			public boolean hasIp() {
				return (this.bitField0_ & 0x1) == 1;
			}

			public String getIp() {
				Object ref = this.ip_;
				if (!(ref instanceof String)) {
					String s = ((ByteString) ref).toStringUtf8();
					this.ip_ = s;
					return s;
				}
				return (String) ref;
			}

			public ByteString getIpBytes() {
				Object ref = this.ip_;
				if ((ref instanceof String)) {
					ByteString b = ByteString.copyFromUtf8((String) ref);
					this.ip_ = b;
					return b;
				}
				return (ByteString) ref;
			}

			public Builder setIp(String value) {
				if (value == null) {
					throw new NullPointerException();
				}
				this.bitField0_ |= 1;
				this.ip_ = value;
				onChanged();
				return this;
			}

			public Builder clearIp() {
				this.bitField0_ &= -2;
				this.ip_ = PCMsgProto.PCItem.getDefaultInstance().getIp();
				onChanged();
				return this;
			}

			public Builder setIpBytes(ByteString value) {
				if (value == null) {
					throw new NullPointerException();
				}
				this.bitField0_ |= 1;
				this.ip_ = value;
				onChanged();
				return this;
			}

			public boolean hasPid() {
				return (this.bitField0_ & 0x2) == 2;
			}

			public int getPid() {
				return this.pid_;
			}

			public Builder setPid(int value) {
				this.bitField0_ |= 2;
				this.pid_ = value;
				onChanged();
				return this;
			}

			public Builder clearPid() {
				this.bitField0_ &= -3;
				this.pid_ = 0;
				onChanged();
				return this;
			}

			public boolean hasUser() {
				return (this.bitField0_ & 0x4) == 4;
			}

			public String getUser() {
				Object ref = this.user_;
				if (!(ref instanceof String)) {
					String s = ((ByteString) ref).toStringUtf8();
					this.user_ = s;
					return s;
				}
				return (String) ref;
			}

			public ByteString getUserBytes() {
				Object ref = this.user_;
				if ((ref instanceof String)) {
					ByteString b = ByteString.copyFromUtf8((String) ref);
					this.user_ = b;
					return b;
				}
				return (ByteString) ref;
			}

			public Builder setUser(String value) {
				if (value == null) {
					throw new NullPointerException();
				}
				this.bitField0_ |= 4;
				this.user_ = value;
				onChanged();
				return this;
			}

			public Builder clearUser() {
				this.bitField0_ &= -5;
				this.user_ = PCMsgProto.PCItem.getDefaultInstance().getUser();
				onChanged();
				return this;
			}

			public Builder setUserBytes(ByteString value) {
				if (value == null) {
					throw new NullPointerException();
				}
				this.bitField0_ |= 4;
				this.user_ = value;
				onChanged();
				return this;
			}

			public boolean hasVersion() {
				return (this.bitField0_ & 0x8) == 8;
			}

			public String getVersion() {
				Object ref = this.version_;
				if (!(ref instanceof String)) {
					String s = ((ByteString) ref).toStringUtf8();
					this.version_ = s;
					return s;
				}
				return (String) ref;
			}

			public ByteString getVersionBytes() {
				Object ref = this.version_;
				if ((ref instanceof String)) {
					ByteString b = ByteString.copyFromUtf8((String) ref);
					this.version_ = b;
					return b;
				}
				return (ByteString) ref;
			}

			public Builder setVersion(String value) {
				if (value == null) {
					throw new NullPointerException();
				}
				this.bitField0_ |= 8;
				this.version_ = value;
				onChanged();
				return this;
			}

			public Builder clearVersion() {
				this.bitField0_ &= -9;
				this.version_ = PCMsgProto.PCItem.getDefaultInstance().getVersion();
				onChanged();
				return this;
			}

			public Builder setVersionBytes(ByteString value) {
				if (value == null) {
					throw new NullPointerException();
				}
				this.bitField0_ |= 8;
				this.version_ = value;
				onChanged();
				return this;
			}

			public boolean hasProductId() {
				return (this.bitField0_ & 0x10) == 16;
			}

			public int getProductId() {
				return this.productId_;
			}

			public Builder setProductId(int value) {
				this.bitField0_ |= 16;
				this.productId_ = value;
				onChanged();
				return this;
			}

			public Builder clearProductId() {
				this.bitField0_ &= -17;
				this.productId_ = 0;
				onChanged();
				return this;
			}

			public boolean hasAesmsg() {
				return (this.bitField0_ & 0x20) == 32;
			}

			public ByteString getAesmsg() {
				return this.aesmsg_;
			}

			public Builder setAesmsg(ByteString value) {
				if (value == null) {
					throw new NullPointerException();
				}
				this.bitField0_ |= 32;
				this.aesmsg_ = value;
				onChanged();
				return this;
			}

			public Builder clearAesmsg() {
				this.bitField0_ &= -33;
				this.aesmsg_ = PCMsgProto.PCItem.getDefaultInstance().getAesmsg();
				onChanged();
				return this;
			}

			public boolean hasMsgId() {
				return (this.bitField0_ & 0x40) == 64;
			}

			public int getMsgId() {
				return this.msgId_;
			}

			public Builder setMsgId(int value) {
				this.bitField0_ |= 64;
				this.msgId_ = value;
				onChanged();
				return this;
			}

			public Builder clearMsgId() {
				this.bitField0_ &= -65;
				this.msgId_ = 0;
				onChanged();
				return this;
			}
		}
	}

	public static abstract interface PCItemOrBuilder extends MessageOrBuilder {
		public abstract boolean hasIp();

		public abstract String getIp();

		public abstract ByteString getIpBytes();

		public abstract boolean hasPid();

		public abstract int getPid();

		public abstract boolean hasUser();

		public abstract String getUser();

		public abstract ByteString getUserBytes();

		public abstract boolean hasVersion();

		public abstract String getVersion();

		public abstract ByteString getVersionBytes();

		public abstract boolean hasProductId();

		public abstract int getProductId();

		public abstract boolean hasAesmsg();

		public abstract ByteString getAesmsg();

		public abstract boolean hasMsgId();

		public abstract int getMsgId();
	}

	public static final class PCUrl extends GeneratedMessage implements PCMsgProto.PCUrlOrBuilder {
		private static final PCUrl defaultInstance;
		private final UnknownFieldSet unknownFields;
		public static Parser<PCUrl> PARSER = new AbstractParser() {
			public PCMsgProto.PCUrl parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
				return new PCMsgProto.PCUrl(input, extensionRegistry);
			}
		};
		private int bitField0_;
		public static final int OPRTIME_FIELD_NUMBER = 1;
		private long oprTime_;
		public static final int FUNCIONID_FIELD_NUMBER = 2;
		private int funcionId_;
		public static final int URL_FIELD_NUMBER = 3;
		private Object url_;
		private byte memoizedIsInitialized = -1;

		private int memoizedSerializedSize = -1;
		private static final long serialVersionUID = 0L;

		static {
			defaultInstance = new PCUrl(true);
			defaultInstance.initFields();
		}

		private PCUrl(GeneratedMessage.Builder<?> builder) {
			super();
			this.unknownFields = builder.getUnknownFields();
		}

		private PCUrl(boolean noInit) {
			this.unknownFields = UnknownFieldSet.getDefaultInstance();
		}

		public static PCUrl getDefaultInstance() {
			return defaultInstance;
		}

		public PCUrl getDefaultInstanceForType() {
			return defaultInstance;
		}

		public final UnknownFieldSet getUnknownFields() {
			return this.unknownFields;
		}

		private PCUrl(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
			initFields();
			int mutable_bitField0_ = 0;
			UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
			try {
				boolean done = false;
				while (!done) {
					int tag = input.readTag();
					switch (tag) {
					case 0:
						done = true;
						break;
					default:
						if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
							done = true;
						}
						break;
					case 8:
						this.bitField0_ |= 1;
						this.oprTime_ = input.readInt64();
						break;
					case 16:
						this.bitField0_ |= 2;
						this.funcionId_ = input.readInt32();
						break;
					case 26:
						this.bitField0_ |= 4;
						this.url_ = input.readBytes();
					}
				}
			} catch (InvalidProtocolBufferException e) {
				throw e.setUnfinishedMessage(this);
			} catch (IOException e) {
				throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
			} finally {
				this.unknownFields = unknownFields.build();
				makeExtensionsImmutable();
			}
		}

		public static final Descriptors.Descriptor getDescriptor() {
			return PCMsgProto.internal_static_pbmsg_PCUrl_descriptor;
		}

		protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
			return PCMsgProto.internal_static_pbmsg_PCUrl_fieldAccessorTable.ensureFieldAccessorsInitialized(PCUrl.class, Builder.class);
		}

		public Parser<PCUrl> getParserForType() {
			return PARSER;
		}

		public boolean hasOprTime() {
			return (this.bitField0_ & 0x1) == 1;
		}

		public long getOprTime() {
			return this.oprTime_;
		}

		public boolean hasFuncionId() {
			return (this.bitField0_ & 0x2) == 2;
		}

		public int getFuncionId() {
			return this.funcionId_;
		}

		public boolean hasUrl() {
			return (this.bitField0_ & 0x4) == 4;
		}

		public String getUrl() {
			Object ref = this.url_;
			if ((ref instanceof String)) {
				return (String) ref;
			}
			ByteString bs = (ByteString) ref;
			String s = bs.toStringUtf8();
			if (bs.isValidUtf8()) {
				this.url_ = s;
			}
			return s;
		}

		public ByteString getUrlBytes() {
			Object ref = this.url_;
			if ((ref instanceof String)) {
				ByteString b = ByteString.copyFromUtf8((String) ref);
				this.url_ = b;
				return b;
			}
			return (ByteString) ref;
		}

		private void initFields() {
			this.oprTime_ = 0L;
			this.funcionId_ = 0;
			this.url_ = "";
		}

		public final boolean isInitialized() {
			byte isInitialized = this.memoizedIsInitialized;
			if (isInitialized != -1)
				return isInitialized == 1;

			if (!hasOprTime()) {
				this.memoizedIsInitialized = 0;
				return false;
			}
			if (!hasFuncionId()) {
				this.memoizedIsInitialized = 0;
				return false;
			}
			if (!hasUrl()) {
				this.memoizedIsInitialized = 0;
				return false;
			}
			this.memoizedIsInitialized = 1;
			return true;
		}

		public void writeTo(CodedOutputStream output) throws IOException {
			getSerializedSize();
			if ((this.bitField0_ & 0x1) == 1) {
				output.writeInt64(1, this.oprTime_);
			}
			if ((this.bitField0_ & 0x2) == 2) {
				output.writeInt32(2, this.funcionId_);
			}
			if ((this.bitField0_ & 0x4) == 4) {
				output.writeBytes(3, getUrlBytes());
			}
			getUnknownFields().writeTo(output);
		}

		public int getSerializedSize() {
			int size = this.memoizedSerializedSize;
			if (size != -1)
				return size;

			size = 0;
			if ((this.bitField0_ & 0x1) == 1) {
				size = size + CodedOutputStream.computeInt64Size(1, this.oprTime_);
			}
			if ((this.bitField0_ & 0x2) == 2) {
				size = size + CodedOutputStream.computeInt32Size(2, this.funcionId_);
			}
			if ((this.bitField0_ & 0x4) == 4) {
				size = size + CodedOutputStream.computeBytesSize(3, getUrlBytes());
			}
			size += getUnknownFields().getSerializedSize();
			this.memoizedSerializedSize = size;
			return size;
		}

		protected Object writeReplace() throws ObjectStreamException {
			return super.writeReplace();
		}

		public static PCUrl parseFrom(ByteString data) throws InvalidProtocolBufferException {
			return (PCUrl) PARSER.parseFrom(data);
		}

		public static PCUrl parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
			return (PCUrl) PARSER.parseFrom(data, extensionRegistry);
		}

		public static PCUrl parseFrom(byte[] data) throws InvalidProtocolBufferException {
			return (PCUrl) PARSER.parseFrom(data);
		}

		public static PCUrl parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
			return (PCUrl) PARSER.parseFrom(data, extensionRegistry);
		}

		public static PCUrl parseFrom(InputStream input) throws IOException {
			return (PCUrl) PARSER.parseFrom(input);
		}

		public static PCUrl parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
			return (PCUrl) PARSER.parseFrom(input, extensionRegistry);
		}

		public static PCUrl parseDelimitedFrom(InputStream input) throws IOException {
			return (PCUrl) PARSER.parseDelimitedFrom(input);
		}

		public static PCUrl parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
			return (PCUrl) PARSER.parseDelimitedFrom(input, extensionRegistry);
		}

		public static PCUrl parseFrom(CodedInputStream input) throws IOException {
			return (PCUrl) PARSER.parseFrom(input);
		}

		public static PCUrl parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
			return (PCUrl) PARSER.parseFrom(input, extensionRegistry);
		}

		public static Builder newBuilder() {
			return Builder.create();
		}

		public Builder newBuilderForType() {
			return newBuilder();
		}

		public static Builder newBuilder(PCUrl prototype) {
			return newBuilder().mergeFrom(prototype);
		}

		public Builder toBuilder() {
			return newBuilder(this);
		}

		protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
			Builder builder = new Builder(parent);
			return builder;
		}

		public static final class Builder extends GeneratedMessage.Builder<Builder> implements PCMsgProto.PCUrlOrBuilder {
			private int bitField0_;
			private long oprTime_;
			private int funcionId_;
			private Object url_ = "";

			public static final Descriptors.Descriptor getDescriptor() {
				return PCMsgProto.internal_static_pbmsg_PCUrl_descriptor;
			}

			protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
				return PCMsgProto.internal_static_pbmsg_PCUrl_fieldAccessorTable.ensureFieldAccessorsInitialized(PCMsgProto.PCUrl.class, Builder.class);
			}

			private Builder() {
				maybeForceBuilderInitialization();
			}

			private Builder(GeneratedMessage.BuilderParent parent) {
				super();
				maybeForceBuilderInitialization();
			}

			private void maybeForceBuilderInitialization() {
				PCMsgProto.PCUrl.newBuilder();
			}

			private static Builder create() {
				return new Builder();
			}

			public Builder clear() {
				super.clear();
				this.oprTime_ = 0L;
				this.bitField0_ &= -2;
				this.funcionId_ = 0;
				this.bitField0_ &= -3;
				this.url_ = "";
				this.bitField0_ &= -5;
				return this;
			}

			public Builder clone() {
				return create().mergeFrom(buildPartial());
			}

			public Descriptors.Descriptor getDescriptorForType() {
				return PCMsgProto.internal_static_pbmsg_PCUrl_descriptor;
			}

			public PCMsgProto.PCUrl getDefaultInstanceForType() {
				return PCMsgProto.PCUrl.getDefaultInstance();
			}

			public PCMsgProto.PCUrl build() {
				PCMsgProto.PCUrl result = buildPartial();
				if (!result.isInitialized()) {
					throw newUninitializedMessageException(result);
				}
				return result;
			}

			public PCMsgProto.PCUrl buildPartial() {
				PCMsgProto.PCUrl result = new PCMsgProto.PCUrl(this);
				int from_bitField0_ = this.bitField0_;
				int to_bitField0_ = 0;
				if ((from_bitField0_ & 0x1) == 1) {
					to_bitField0_ |= 1;
				}
				result.oprTime_ = this.oprTime_;
				if ((from_bitField0_ & 0x2) == 2) {
					to_bitField0_ |= 2;
				}
				result.funcionId_ = this.funcionId_;
				if ((from_bitField0_ & 0x4) == 4) {
					to_bitField0_ |= 4;
				}
				result.url_ = this.url_;
				result.bitField0_ = to_bitField0_;
				onBuilt();
				return result;
			}

			public Builder mergeFrom(Message other) {
				if ((other instanceof PCMsgProto.PCUrl)) {
					return mergeFrom((PCMsgProto.PCUrl) other);
				}
				super.mergeFrom(other);
				return this;
			}

			public Builder mergeFrom(PCMsgProto.PCUrl other) {
				if (other == PCMsgProto.PCUrl.getDefaultInstance())
					return this;
				if (other.hasOprTime()) {
					setOprTime(other.getOprTime());
				}
				if (other.hasFuncionId()) {
					setFuncionId(other.getFuncionId());
				}
				if (other.hasUrl()) {
					this.bitField0_ |= 4;
					this.url_ = other.url_;
					onChanged();
				}
				mergeUnknownFields(other.getUnknownFields());
				return this;
			}

			public final boolean isInitialized() {
				if (!hasOprTime()) {
					return false;
				}
				if (!hasFuncionId()) {
					return false;
				}
				if (!hasUrl()) {
					return false;
				}
				return true;
			}

			public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
				PCMsgProto.PCUrl parsedMessage = null;
				try {
					parsedMessage = (PCMsgProto.PCUrl) PCMsgProto.PCUrl.PARSER.parsePartialFrom(input, extensionRegistry);
				} catch (InvalidProtocolBufferException e) {
					parsedMessage = (PCMsgProto.PCUrl) e.getUnfinishedMessage();
					throw e;
				} finally {
					if (parsedMessage != null) {
						mergeFrom(parsedMessage);
					}
				}
				return this;
			}

			public boolean hasOprTime() {
				return (this.bitField0_ & 0x1) == 1;
			}

			public long getOprTime() {
				return this.oprTime_;
			}

			public Builder setOprTime(long value) {
				this.bitField0_ |= 1;
				this.oprTime_ = value;
				onChanged();
				return this;
			}

			public Builder clearOprTime() {
				this.bitField0_ &= -2;
				this.oprTime_ = 0L;
				onChanged();
				return this;
			}

			public boolean hasFuncionId() {
				return (this.bitField0_ & 0x2) == 2;
			}

			public int getFuncionId() {
				return this.funcionId_;
			}

			public Builder setFuncionId(int value) {
				this.bitField0_ |= 2;
				this.funcionId_ = value;
				onChanged();
				return this;
			}

			public Builder clearFuncionId() {
				this.bitField0_ &= -3;
				this.funcionId_ = 0;
				onChanged();
				return this;
			}

			public boolean hasUrl() {
				return (this.bitField0_ & 0x4) == 4;
			}

			public String getUrl() {
				Object ref = this.url_;
				if (!(ref instanceof String)) {
					String s = ((ByteString) ref).toStringUtf8();
					this.url_ = s;
					return s;
				}
				return (String) ref;
			}

			public ByteString getUrlBytes() {
				Object ref = this.url_;
				if ((ref instanceof String)) {
					ByteString b = ByteString.copyFromUtf8((String) ref);
					this.url_ = b;
					return b;
				}
				return (ByteString) ref;
			}

			public Builder setUrl(String value) {
				if (value == null) {
					throw new NullPointerException();
				}
				this.bitField0_ |= 4;
				this.url_ = value;
				onChanged();
				return this;
			}

			public Builder clearUrl() {
				this.bitField0_ &= -5;
				this.url_ = PCMsgProto.PCUrl.getDefaultInstance().getUrl();
				onChanged();
				return this;
			}

			public Builder setUrlBytes(ByteString value) {
				if (value == null) {
					throw new NullPointerException();
				}
				this.bitField0_ |= 4;
				this.url_ = value;
				onChanged();
				return this;
			}
		}
	}

	public static abstract interface PCUrlOrBuilder extends MessageOrBuilder {
		public abstract boolean hasOprTime();

		public abstract long getOprTime();

		public abstract boolean hasFuncionId();

		public abstract int getFuncionId();

		public abstract boolean hasUrl();

		public abstract String getUrl();

		public abstract ByteString getUrlBytes();
	}

	public static final class PCUrls extends GeneratedMessage implements PCMsgProto.PCUrlsOrBuilder {
		private static final PCUrls defaultInstance;
		private final UnknownFieldSet unknownFields;
		public static Parser<PCUrls> PARSER = new AbstractParser() {
			public PCMsgProto.PCUrls parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
				return new PCMsgProto.PCUrls(input, extensionRegistry);
			}
		};
		public static final int PCURL_FIELD_NUMBER = 1;
		private List<PCMsgProto.PCUrl> pcUrl_;
		private byte memoizedIsInitialized = -1;

		private int memoizedSerializedSize = -1;
		private static final long serialVersionUID = 0L;

		static {
			defaultInstance = new PCUrls(true);
			defaultInstance.initFields();
		}

		private PCUrls(GeneratedMessage.Builder<?> builder) {
			super();
			this.unknownFields = builder.getUnknownFields();
		}

		private PCUrls(boolean noInit) {
			this.unknownFields = UnknownFieldSet.getDefaultInstance();
		}

		public static PCUrls getDefaultInstance() {
			return defaultInstance;
		}

		public PCUrls getDefaultInstanceForType() {
			return defaultInstance;
		}

		public final UnknownFieldSet getUnknownFields() {
			return this.unknownFields;
		}

		private PCUrls(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
			initFields();
			int mutable_bitField0_ = 0;
			UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
			try {
				boolean done = false;
				while (!done) {
					int tag = input.readTag();
					switch (tag) {
					case 0:
						done = true;
						break;
					default:
						if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
							done = true;
						}
						break;
					case 10:
						if ((mutable_bitField0_ & 0x1) != 1) {
							this.pcUrl_ = new ArrayList();
							mutable_bitField0_ |= 1;
						}
						this.pcUrl_.add((PCMsgProto.PCUrl) input.readMessage(PCMsgProto.PCUrl.PARSER, extensionRegistry));
					}
				}
			} catch (InvalidProtocolBufferException e) {
				throw e.setUnfinishedMessage(this);
			} catch (IOException e) {
				throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
			} finally {
				if ((mutable_bitField0_ & 0x1) == 1) {
					this.pcUrl_ = Collections.unmodifiableList(this.pcUrl_);
				}
				this.unknownFields = unknownFields.build();
				makeExtensionsImmutable();
			}
		}

		public static final Descriptors.Descriptor getDescriptor() {
			return PCMsgProto.internal_static_pbmsg_PCUrls_descriptor;
		}

		protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
			return PCMsgProto.internal_static_pbmsg_PCUrls_fieldAccessorTable.ensureFieldAccessorsInitialized(PCUrls.class, Builder.class);
		}

		public Parser<PCUrls> getParserForType() {
			return PARSER;
		}

		public List<PCMsgProto.PCUrl> getPcUrlList() {
			return this.pcUrl_;
		}

		public List<? extends PCMsgProto.PCUrlOrBuilder> getPcUrlOrBuilderList() {
			return this.pcUrl_;
		}

		public int getPcUrlCount() {
			return this.pcUrl_.size();
		}

		public PCMsgProto.PCUrl getPcUrl(int index) {
			return (PCMsgProto.PCUrl) this.pcUrl_.get(index);
		}

		public PCMsgProto.PCUrlOrBuilder getPcUrlOrBuilder(int index) {
			return (PCMsgProto.PCUrlOrBuilder) this.pcUrl_.get(index);
		}

		private void initFields() {
			this.pcUrl_ = Collections.emptyList();
		}

		public final boolean isInitialized() {
			byte isInitialized = this.memoizedIsInitialized;
			if (isInitialized != -1)
				return isInitialized == 1;

			for (int i = 0; i < getPcUrlCount(); i++) {
				if (!getPcUrl(i).isInitialized()) {
					this.memoizedIsInitialized = 0;
					return false;
				}
			}
			this.memoizedIsInitialized = 1;
			return true;
		}

		public void writeTo(CodedOutputStream output) throws IOException {
			getSerializedSize();
			for (int i = 0; i < this.pcUrl_.size(); i++) {
				output.writeMessage(1, (MessageLite) this.pcUrl_.get(i));
			}
			getUnknownFields().writeTo(output);
		}

		public int getSerializedSize() {
			int size = this.memoizedSerializedSize;
			if (size != -1)
				return size;

			size = 0;
			for (int i = 0; i < this.pcUrl_.size(); i++) {
				size = size + CodedOutputStream.computeMessageSize(1, (MessageLite) this.pcUrl_.get(i));
			}
			size += getUnknownFields().getSerializedSize();
			this.memoizedSerializedSize = size;
			return size;
		}

		protected Object writeReplace() throws ObjectStreamException {
			return super.writeReplace();
		}

		public static PCUrls parseFrom(ByteString data) throws InvalidProtocolBufferException {
			return (PCUrls) PARSER.parseFrom(data);
		}

		public static PCUrls parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
			return (PCUrls) PARSER.parseFrom(data, extensionRegistry);
		}

		public static PCUrls parseFrom(byte[] data) throws InvalidProtocolBufferException {
			return (PCUrls) PARSER.parseFrom(data);
		}

		public static PCUrls parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
			return (PCUrls) PARSER.parseFrom(data, extensionRegistry);
		}

		public static PCUrls parseFrom(InputStream input) throws IOException {
			return (PCUrls) PARSER.parseFrom(input);
		}

		public static PCUrls parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
			return (PCUrls) PARSER.parseFrom(input, extensionRegistry);
		}

		public static PCUrls parseDelimitedFrom(InputStream input) throws IOException {
			return (PCUrls) PARSER.parseDelimitedFrom(input);
		}

		public static PCUrls parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
			return (PCUrls) PARSER.parseDelimitedFrom(input, extensionRegistry);
		}

		public static PCUrls parseFrom(CodedInputStream input) throws IOException {
			return (PCUrls) PARSER.parseFrom(input);
		}

		public static PCUrls parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
			return (PCUrls) PARSER.parseFrom(input, extensionRegistry);
		}

		public static Builder newBuilder() {
			return Builder.create();
		}

		public Builder newBuilderForType() {
			return newBuilder();
		}

		public static Builder newBuilder(PCUrls prototype) {
			return newBuilder().mergeFrom(prototype);
		}

		public Builder toBuilder() {
			return newBuilder(this);
		}

		protected Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
			Builder builder = new Builder(parent);
			return builder;
		}

		public static final class Builder extends GeneratedMessage.Builder<Builder> implements PCMsgProto.PCUrlsOrBuilder {
			private int bitField0_;
			private List<PCMsgProto.PCUrl> pcUrl_ = Collections.emptyList();
			private RepeatedFieldBuilder<PCMsgProto.PCUrl, PCMsgProto.PCUrl.Builder, PCMsgProto.PCUrlOrBuilder> pcUrlBuilder_;

			public static final Descriptors.Descriptor getDescriptor() {
				return PCMsgProto.internal_static_pbmsg_PCUrls_descriptor;
			}

			protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
				return PCMsgProto.internal_static_pbmsg_PCUrls_fieldAccessorTable.ensureFieldAccessorsInitialized(PCMsgProto.PCUrls.class, Builder.class);
			}

			private Builder() {
				maybeForceBuilderInitialization();
			}

			private Builder(GeneratedMessage.BuilderParent parent) {
				super();
				maybeForceBuilderInitialization();
			}

			private void maybeForceBuilderInitialization() {
				if (PCMsgProto.PCUrls.alwaysUseFieldBuilders)
					getPcUrlFieldBuilder();
			}

			private static Builder create() {
				return new Builder();
			}

			public Builder clear() {
				super.clear();
				if (this.pcUrlBuilder_ == null) {
					this.pcUrl_ = Collections.emptyList();
					this.bitField0_ &= -2;
				} else {
					this.pcUrlBuilder_.clear();
				}
				return this;
			}

			public Builder clone() {
				return create().mergeFrom(buildPartial());
			}

			public Descriptors.Descriptor getDescriptorForType() {
				return PCMsgProto.internal_static_pbmsg_PCUrls_descriptor;
			}

			public PCMsgProto.PCUrls getDefaultInstanceForType() {
				return PCMsgProto.PCUrls.getDefaultInstance();
			}

			public PCMsgProto.PCUrls build() {
				PCMsgProto.PCUrls result = buildPartial();
				if (!result.isInitialized()) {
					throw newUninitializedMessageException(result);
				}
				return result;
			}

			public PCMsgProto.PCUrls buildPartial() {
				PCMsgProto.PCUrls result = new PCMsgProto.PCUrls(this);
				int from_bitField0_ = this.bitField0_;
				if (this.pcUrlBuilder_ == null) {
					if ((this.bitField0_ & 0x1) == 1) {
						this.pcUrl_ = Collections.unmodifiableList(this.pcUrl_);
						this.bitField0_ &= -2;
					}
					result.pcUrl_ = this.pcUrl_;
				} else {
					result.pcUrl_ = this.pcUrlBuilder_.build();
				}
				onBuilt();
				return result;
			}

			public Builder mergeFrom(Message other) {
				if ((other instanceof PCMsgProto.PCUrls)) {
					return mergeFrom((PCMsgProto.PCUrls) other);
				}
				super.mergeFrom(other);
				return this;
			}

			public Builder mergeFrom(PCMsgProto.PCUrls other) {
				if (other == PCMsgProto.PCUrls.getDefaultInstance())
					return this;
				if (this.pcUrlBuilder_ == null) {
					if (!other.pcUrl_.isEmpty()) {
						if (this.pcUrl_.isEmpty()) {
							this.pcUrl_ = other.pcUrl_;
							this.bitField0_ &= -2;
						} else {
							ensurePcUrlIsMutable();
							this.pcUrl_.addAll(other.pcUrl_);
						}
						onChanged();
					}
				} else if (!other.pcUrl_.isEmpty()) {
					if (this.pcUrlBuilder_.isEmpty()) {
						this.pcUrlBuilder_.dispose();
						this.pcUrlBuilder_ = null;
						this.pcUrl_ = other.pcUrl_;
						this.bitField0_ &= -2;
						this.pcUrlBuilder_ = (PCMsgProto.PCUrls.alwaysUseFieldBuilders ? getPcUrlFieldBuilder() : null);
					} else {
						this.pcUrlBuilder_.addAllMessages(other.pcUrl_);
					}
				}

				mergeUnknownFields(other.getUnknownFields());
				return this;
			}

			public final boolean isInitialized() {
				for (int i = 0; i < getPcUrlCount(); i++) {
					if (!getPcUrl(i).isInitialized()) {
						return false;
					}
				}
				return true;
			}

			public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
				PCMsgProto.PCUrls parsedMessage = null;
				try {
					parsedMessage = (PCMsgProto.PCUrls) PCMsgProto.PCUrls.PARSER.parsePartialFrom(input, extensionRegistry);
				} catch (InvalidProtocolBufferException e) {
					parsedMessage = (PCMsgProto.PCUrls) e.getUnfinishedMessage();
					throw e;
				} finally {
					if (parsedMessage != null) {
						mergeFrom(parsedMessage);
					}
				}
				return this;
			}

			private void ensurePcUrlIsMutable() {
				if ((this.bitField0_ & 0x1) != 1) {
					this.pcUrl_ = new ArrayList(this.pcUrl_);
					this.bitField0_ |= 1;
				}
			}

			public List<PCMsgProto.PCUrl> getPcUrlList() {
				if (this.pcUrlBuilder_ == null) {
					return Collections.unmodifiableList(this.pcUrl_);
				}
				return this.pcUrlBuilder_.getMessageList();
			}

			public int getPcUrlCount() {
				if (this.pcUrlBuilder_ == null) {
					return this.pcUrl_.size();
				}
				return this.pcUrlBuilder_.getCount();
			}

			public PCMsgProto.PCUrl getPcUrl(int index) {
				if (this.pcUrlBuilder_ == null) {
					return (PCMsgProto.PCUrl) this.pcUrl_.get(index);
				}
				return (PCMsgProto.PCUrl) this.pcUrlBuilder_.getMessage(index);
			}

			public Builder setPcUrl(int index, PCMsgProto.PCUrl value) {
				if (this.pcUrlBuilder_ == null) {
					if (value == null) {
						throw new NullPointerException();
					}
					ensurePcUrlIsMutable();
					this.pcUrl_.set(index, value);
					onChanged();
				} else {
					this.pcUrlBuilder_.setMessage(index, value);
				}
				return this;
			}

			public Builder setPcUrl(int index, PCMsgProto.PCUrl.Builder builderForValue) {
				if (this.pcUrlBuilder_ == null) {
					ensurePcUrlIsMutable();
					this.pcUrl_.set(index, builderForValue.build());
					onChanged();
				} else {
					this.pcUrlBuilder_.setMessage(index, builderForValue.build());
				}
				return this;
			}

			public Builder addPcUrl(PCMsgProto.PCUrl value) {
				if (this.pcUrlBuilder_ == null) {
					if (value == null) {
						throw new NullPointerException();
					}
					ensurePcUrlIsMutable();
					this.pcUrl_.add(value);
					onChanged();
				} else {
					this.pcUrlBuilder_.addMessage(value);
				}
				return this;
			}

			public Builder addPcUrl(int index, PCMsgProto.PCUrl value) {
				if (this.pcUrlBuilder_ == null) {
					if (value == null) {
						throw new NullPointerException();
					}
					ensurePcUrlIsMutable();
					this.pcUrl_.add(index, value);
					onChanged();
				} else {
					this.pcUrlBuilder_.addMessage(index, value);
				}
				return this;
			}

			public Builder addPcUrl(PCMsgProto.PCUrl.Builder builderForValue) {
				if (this.pcUrlBuilder_ == null) {
					ensurePcUrlIsMutable();
					this.pcUrl_.add(builderForValue.build());
					onChanged();
				} else {
					this.pcUrlBuilder_.addMessage(builderForValue.build());
				}
				return this;
			}

			public Builder addPcUrl(int index, PCMsgProto.PCUrl.Builder builderForValue) {
				if (this.pcUrlBuilder_ == null) {
					ensurePcUrlIsMutable();
					this.pcUrl_.add(index, builderForValue.build());
					onChanged();
				} else {
					this.pcUrlBuilder_.addMessage(index, builderForValue.build());
				}
				return this;
			}

			public Builder addAllPcUrl(Iterable<? extends PCMsgProto.PCUrl> values) {
				if (this.pcUrlBuilder_ == null) {
					ensurePcUrlIsMutable();
					GeneratedMessage.Builder.addAll(values, this.pcUrl_);
					onChanged();
				} else {
					this.pcUrlBuilder_.addAllMessages(values);
				}
				return this;
			}

			public Builder clearPcUrl() {
				if (this.pcUrlBuilder_ == null) {
					this.pcUrl_ = Collections.emptyList();
					this.bitField0_ &= -2;
					onChanged();
				} else {
					this.pcUrlBuilder_.clear();
				}
				return this;
			}

			public Builder removePcUrl(int index) {
				if (this.pcUrlBuilder_ == null) {
					ensurePcUrlIsMutable();
					this.pcUrl_.remove(index);
					onChanged();
				} else {
					this.pcUrlBuilder_.remove(index);
				}
				return this;
			}

			public PCMsgProto.PCUrl.Builder getPcUrlBuilder(int index) {
				return (PCMsgProto.PCUrl.Builder) getPcUrlFieldBuilder().getBuilder(index);
			}

			public PCMsgProto.PCUrlOrBuilder getPcUrlOrBuilder(int index) {
				if (this.pcUrlBuilder_ == null)
					return (PCMsgProto.PCUrlOrBuilder) this.pcUrl_.get(index);
				return (PCMsgProto.PCUrlOrBuilder) this.pcUrlBuilder_.getMessageOrBuilder(index);
			}

			public List<? extends PCMsgProto.PCUrlOrBuilder> getPcUrlOrBuilderList() {
				if (this.pcUrlBuilder_ != null) {
					return this.pcUrlBuilder_.getMessageOrBuilderList();
				}
				return Collections.unmodifiableList(this.pcUrl_);
			}

			public PCMsgProto.PCUrl.Builder addPcUrlBuilder() {
				return (PCMsgProto.PCUrl.Builder) getPcUrlFieldBuilder().addBuilder(PCMsgProto.PCUrl.getDefaultInstance());
			}

			public PCMsgProto.PCUrl.Builder addPcUrlBuilder(int index) {
				return (PCMsgProto.PCUrl.Builder) getPcUrlFieldBuilder().addBuilder(index, PCMsgProto.PCUrl.getDefaultInstance());
			}

			public List<PCMsgProto.PCUrl.Builder> getPcUrlBuilderList() {
				return getPcUrlFieldBuilder().getBuilderList();
			}

			private RepeatedFieldBuilder<PCMsgProto.PCUrl, PCMsgProto.PCUrl.Builder, PCMsgProto.PCUrlOrBuilder> getPcUrlFieldBuilder() {
				if (this.pcUrlBuilder_ == null) {
					this.pcUrlBuilder_ = new RepeatedFieldBuilder(this.pcUrl_, (this.bitField0_ & 0x1) == 1, getParentForChildren(), isClean());
					this.pcUrl_ = null;
				}
				return this.pcUrlBuilder_;
			}
		}
	}

	public static abstract interface PCUrlsOrBuilder extends MessageOrBuilder {
		public abstract List<PCMsgProto.PCUrl> getPcUrlList();

		public abstract PCMsgProto.PCUrl getPcUrl(int paramInt);

		public abstract int getPcUrlCount();

		public abstract List<? extends PCMsgProto.PCUrlOrBuilder> getPcUrlOrBuilderList();

		public abstract PCMsgProto.PCUrlOrBuilder getPcUrlOrBuilder(int paramInt);
	}
}