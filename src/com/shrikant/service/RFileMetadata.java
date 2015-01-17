/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.shrikant.service;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RFileMetadata implements org.apache.thrift.TBase<RFileMetadata, RFileMetadata._Fields>, java.io.Serializable, Cloneable, Comparable<RFileMetadata> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RFileMetadata");

  private static final org.apache.thrift.protocol.TField FILENAME_FIELD_DESC = new org.apache.thrift.protocol.TField("filename", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField CREATED_FIELD_DESC = new org.apache.thrift.protocol.TField("created", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField UPDATED_FIELD_DESC = new org.apache.thrift.protocol.TField("updated", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField DELETED_FIELD_DESC = new org.apache.thrift.protocol.TField("deleted", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField VERSION_FIELD_DESC = new org.apache.thrift.protocol.TField("version", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField OWNER_FIELD_DESC = new org.apache.thrift.protocol.TField("owner", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField CONTENT_LENGTH_FIELD_DESC = new org.apache.thrift.protocol.TField("contentLength", org.apache.thrift.protocol.TType.I32, (short)7);
  private static final org.apache.thrift.protocol.TField CONTENT_HASH_FIELD_DESC = new org.apache.thrift.protocol.TField("contentHash", org.apache.thrift.protocol.TType.STRING, (short)8);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new RFileMetadataStandardSchemeFactory());
    schemes.put(TupleScheme.class, new RFileMetadataTupleSchemeFactory());
  }

  public String filename; // optional
  public long created; // optional
  public long updated; // optional
  public long deleted; // optional
  public int version; // optional
  public String owner; // optional
  public int contentLength; // optional
  public String contentHash; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    FILENAME((short)1, "filename"),
    CREATED((short)2, "created"),
    UPDATED((short)3, "updated"),
    DELETED((short)4, "deleted"),
    VERSION((short)5, "version"),
    OWNER((short)6, "owner"),
    CONTENT_LENGTH((short)7, "contentLength"),
    CONTENT_HASH((short)8, "contentHash");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // FILENAME
          return FILENAME;
        case 2: // CREATED
          return CREATED;
        case 3: // UPDATED
          return UPDATED;
        case 4: // DELETED
          return DELETED;
        case 5: // VERSION
          return VERSION;
        case 6: // OWNER
          return OWNER;
        case 7: // CONTENT_LENGTH
          return CONTENT_LENGTH;
        case 8: // CONTENT_HASH
          return CONTENT_HASH;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __CREATED_ISSET_ID = 0;
  private static final int __UPDATED_ISSET_ID = 1;
  private static final int __DELETED_ISSET_ID = 2;
  private static final int __VERSION_ISSET_ID = 3;
  private static final int __CONTENTLENGTH_ISSET_ID = 4;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.FILENAME,_Fields.CREATED,_Fields.UPDATED,_Fields.DELETED,_Fields.VERSION,_Fields.OWNER,_Fields.CONTENT_LENGTH,_Fields.CONTENT_HASH};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.FILENAME, new org.apache.thrift.meta_data.FieldMetaData("filename", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CREATED, new org.apache.thrift.meta_data.FieldMetaData("created", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64        , "Timestamp")));
    tmpMap.put(_Fields.UPDATED, new org.apache.thrift.meta_data.FieldMetaData("updated", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64        , "Timestamp")));
    tmpMap.put(_Fields.DELETED, new org.apache.thrift.meta_data.FieldMetaData("deleted", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64        , "Timestamp")));
    tmpMap.put(_Fields.VERSION, new org.apache.thrift.meta_data.FieldMetaData("version", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.OWNER, new org.apache.thrift.meta_data.FieldMetaData("owner", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , "UserID")));
    tmpMap.put(_Fields.CONTENT_LENGTH, new org.apache.thrift.meta_data.FieldMetaData("contentLength", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.CONTENT_HASH, new org.apache.thrift.meta_data.FieldMetaData("contentHash", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RFileMetadata.class, metaDataMap);
  }

  public RFileMetadata() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RFileMetadata(RFileMetadata other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetFilename()) {
      this.filename = other.filename;
    }
    this.created = other.created;
    this.updated = other.updated;
    this.deleted = other.deleted;
    this.version = other.version;
    if (other.isSetOwner()) {
      this.owner = other.owner;
    }
    this.contentLength = other.contentLength;
    if (other.isSetContentHash()) {
      this.contentHash = other.contentHash;
    }
  }

  public RFileMetadata deepCopy() {
    return new RFileMetadata(this);
  }

  @Override
  public void clear() {
    this.filename = null;
    setCreatedIsSet(false);
    this.created = 0;
    setUpdatedIsSet(false);
    this.updated = 0;
    setDeletedIsSet(false);
    this.deleted = 0;
    setVersionIsSet(false);
    this.version = 0;
    this.owner = null;
    setContentLengthIsSet(false);
    this.contentLength = 0;
    this.contentHash = null;
  }

  public String getFilename() {
    return this.filename;
  }

  public RFileMetadata setFilename(String filename) {
    this.filename = filename;
    return this;
  }

  public void unsetFilename() {
    this.filename = null;
  }

  /** Returns true if field filename is set (has been assigned a value) and false otherwise */
  public boolean isSetFilename() {
    return this.filename != null;
  }

  public void setFilenameIsSet(boolean value) {
    if (!value) {
      this.filename = null;
    }
  }

  public long getCreated() {
    return this.created;
  }

  public RFileMetadata setCreated(long created) {
    this.created = created;
    setCreatedIsSet(true);
    return this;
  }

  public void unsetCreated() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __CREATED_ISSET_ID);
  }

  /** Returns true if field created is set (has been assigned a value) and false otherwise */
  public boolean isSetCreated() {
    return EncodingUtils.testBit(__isset_bitfield, __CREATED_ISSET_ID);
  }

  public void setCreatedIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __CREATED_ISSET_ID, value);
  }

  public long getUpdated() {
    return this.updated;
  }

  public RFileMetadata setUpdated(long updated) {
    this.updated = updated;
    setUpdatedIsSet(true);
    return this;
  }

  public void unsetUpdated() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __UPDATED_ISSET_ID);
  }

  /** Returns true if field updated is set (has been assigned a value) and false otherwise */
  public boolean isSetUpdated() {
    return EncodingUtils.testBit(__isset_bitfield, __UPDATED_ISSET_ID);
  }

  public void setUpdatedIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __UPDATED_ISSET_ID, value);
  }

  public long getDeleted() {
    return this.deleted;
  }

  public RFileMetadata setDeleted(long deleted) {
    this.deleted = deleted;
    setDeletedIsSet(true);
    return this;
  }

  public void unsetDeleted() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __DELETED_ISSET_ID);
  }

  /** Returns true if field deleted is set (has been assigned a value) and false otherwise */
  public boolean isSetDeleted() {
    return EncodingUtils.testBit(__isset_bitfield, __DELETED_ISSET_ID);
  }

  public void setDeletedIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __DELETED_ISSET_ID, value);
  }

  public int getVersion() {
    return this.version;
  }

  public RFileMetadata setVersion(int version) {
    this.version = version;
    setVersionIsSet(true);
    return this;
  }

  public void unsetVersion() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __VERSION_ISSET_ID);
  }

  /** Returns true if field version is set (has been assigned a value) and false otherwise */
  public boolean isSetVersion() {
    return EncodingUtils.testBit(__isset_bitfield, __VERSION_ISSET_ID);
  }

  public void setVersionIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __VERSION_ISSET_ID, value);
  }

  public String getOwner() {
    return this.owner;
  }

  public RFileMetadata setOwner(String owner) {
    this.owner = owner;
    return this;
  }

  public void unsetOwner() {
    this.owner = null;
  }

  /** Returns true if field owner is set (has been assigned a value) and false otherwise */
  public boolean isSetOwner() {
    return this.owner != null;
  }

  public void setOwnerIsSet(boolean value) {
    if (!value) {
      this.owner = null;
    }
  }

  public int getContentLength() {
    return this.contentLength;
  }

  public RFileMetadata setContentLength(int contentLength) {
    this.contentLength = contentLength;
    setContentLengthIsSet(true);
    return this;
  }

  public void unsetContentLength() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __CONTENTLENGTH_ISSET_ID);
  }

  /** Returns true if field contentLength is set (has been assigned a value) and false otherwise */
  public boolean isSetContentLength() {
    return EncodingUtils.testBit(__isset_bitfield, __CONTENTLENGTH_ISSET_ID);
  }

  public void setContentLengthIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __CONTENTLENGTH_ISSET_ID, value);
  }

  public String getContentHash() {
    return this.contentHash;
  }

  public RFileMetadata setContentHash(String contentHash) {
    this.contentHash = contentHash;
    return this;
  }

  public void unsetContentHash() {
    this.contentHash = null;
  }

  /** Returns true if field contentHash is set (has been assigned a value) and false otherwise */
  public boolean isSetContentHash() {
    return this.contentHash != null;
  }

  public void setContentHashIsSet(boolean value) {
    if (!value) {
      this.contentHash = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case FILENAME:
      if (value == null) {
        unsetFilename();
      } else {
        setFilename((String)value);
      }
      break;

    case CREATED:
      if (value == null) {
        unsetCreated();
      } else {
        setCreated((Long)value);
      }
      break;

    case UPDATED:
      if (value == null) {
        unsetUpdated();
      } else {
        setUpdated((Long)value);
      }
      break;

    case DELETED:
      if (value == null) {
        unsetDeleted();
      } else {
        setDeleted((Long)value);
      }
      break;

    case VERSION:
      if (value == null) {
        unsetVersion();
      } else {
        setVersion((Integer)value);
      }
      break;

    case OWNER:
      if (value == null) {
        unsetOwner();
      } else {
        setOwner((String)value);
      }
      break;

    case CONTENT_LENGTH:
      if (value == null) {
        unsetContentLength();
      } else {
        setContentLength((Integer)value);
      }
      break;

    case CONTENT_HASH:
      if (value == null) {
        unsetContentHash();
      } else {
        setContentHash((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case FILENAME:
      return getFilename();

    case CREATED:
      return Long.valueOf(getCreated());

    case UPDATED:
      return Long.valueOf(getUpdated());

    case DELETED:
      return Long.valueOf(getDeleted());

    case VERSION:
      return Integer.valueOf(getVersion());

    case OWNER:
      return getOwner();

    case CONTENT_LENGTH:
      return Integer.valueOf(getContentLength());

    case CONTENT_HASH:
      return getContentHash();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case FILENAME:
      return isSetFilename();
    case CREATED:
      return isSetCreated();
    case UPDATED:
      return isSetUpdated();
    case DELETED:
      return isSetDeleted();
    case VERSION:
      return isSetVersion();
    case OWNER:
      return isSetOwner();
    case CONTENT_LENGTH:
      return isSetContentLength();
    case CONTENT_HASH:
      return isSetContentHash();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof RFileMetadata)
      return this.equals((RFileMetadata)that);
    return false;
  }

  public boolean equals(RFileMetadata that) {
    if (that == null)
      return false;

    boolean this_present_filename = true && this.isSetFilename();
    boolean that_present_filename = true && that.isSetFilename();
    if (this_present_filename || that_present_filename) {
      if (!(this_present_filename && that_present_filename))
        return false;
      if (!this.filename.equals(that.filename))
        return false;
    }

    boolean this_present_created = true && this.isSetCreated();
    boolean that_present_created = true && that.isSetCreated();
    if (this_present_created || that_present_created) {
      if (!(this_present_created && that_present_created))
        return false;
      if (this.created != that.created)
        return false;
    }

    boolean this_present_updated = true && this.isSetUpdated();
    boolean that_present_updated = true && that.isSetUpdated();
    if (this_present_updated || that_present_updated) {
      if (!(this_present_updated && that_present_updated))
        return false;
      if (this.updated != that.updated)
        return false;
    }

    boolean this_present_deleted = true && this.isSetDeleted();
    boolean that_present_deleted = true && that.isSetDeleted();
    if (this_present_deleted || that_present_deleted) {
      if (!(this_present_deleted && that_present_deleted))
        return false;
      if (this.deleted != that.deleted)
        return false;
    }

    boolean this_present_version = true && this.isSetVersion();
    boolean that_present_version = true && that.isSetVersion();
    if (this_present_version || that_present_version) {
      if (!(this_present_version && that_present_version))
        return false;
      if (this.version != that.version)
        return false;
    }

    boolean this_present_owner = true && this.isSetOwner();
    boolean that_present_owner = true && that.isSetOwner();
    if (this_present_owner || that_present_owner) {
      if (!(this_present_owner && that_present_owner))
        return false;
      if (!this.owner.equals(that.owner))
        return false;
    }

    boolean this_present_contentLength = true && this.isSetContentLength();
    boolean that_present_contentLength = true && that.isSetContentLength();
    if (this_present_contentLength || that_present_contentLength) {
      if (!(this_present_contentLength && that_present_contentLength))
        return false;
      if (this.contentLength != that.contentLength)
        return false;
    }

    boolean this_present_contentHash = true && this.isSetContentHash();
    boolean that_present_contentHash = true && that.isSetContentHash();
    if (this_present_contentHash || that_present_contentHash) {
      if (!(this_present_contentHash && that_present_contentHash))
        return false;
      if (!this.contentHash.equals(that.contentHash))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(RFileMetadata other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetFilename()).compareTo(other.isSetFilename());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFilename()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.filename, other.filename);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCreated()).compareTo(other.isSetCreated());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCreated()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.created, other.created);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUpdated()).compareTo(other.isSetUpdated());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUpdated()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.updated, other.updated);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDeleted()).compareTo(other.isSetDeleted());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDeleted()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.deleted, other.deleted);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetVersion()).compareTo(other.isSetVersion());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVersion()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.version, other.version);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetOwner()).compareTo(other.isSetOwner());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOwner()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.owner, other.owner);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetContentLength()).compareTo(other.isSetContentLength());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetContentLength()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.contentLength, other.contentLength);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetContentHash()).compareTo(other.isSetContentHash());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetContentHash()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.contentHash, other.contentHash);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("RFileMetadata(");
    boolean first = true;

    if (isSetFilename()) {
      sb.append("filename:");
      if (this.filename == null) {
        sb.append("null");
      } else {
        sb.append(this.filename);
      }
      first = false;
    }
    if (isSetCreated()) {
      if (!first) sb.append(", ");
      sb.append("created:");
      sb.append(this.created);
      first = false;
    }
    if (isSetUpdated()) {
      if (!first) sb.append(", ");
      sb.append("updated:");
      sb.append(this.updated);
      first = false;
    }
    if (isSetDeleted()) {
      if (!first) sb.append(", ");
      sb.append("deleted:");
      sb.append(this.deleted);
      first = false;
    }
    if (isSetVersion()) {
      if (!first) sb.append(", ");
      sb.append("version:");
      sb.append(this.version);
      first = false;
    }
    if (isSetOwner()) {
      if (!first) sb.append(", ");
      sb.append("owner:");
      if (this.owner == null) {
        sb.append("null");
      } else {
        sb.append(this.owner);
      }
      first = false;
    }
    if (isSetContentLength()) {
      if (!first) sb.append(", ");
      sb.append("contentLength:");
      sb.append(this.contentLength);
      first = false;
    }
    if (isSetContentHash()) {
      if (!first) sb.append(", ");
      sb.append("contentHash:");
      if (this.contentHash == null) {
        sb.append("null");
      } else {
        sb.append(this.contentHash);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RFileMetadataStandardSchemeFactory implements SchemeFactory {
    public RFileMetadataStandardScheme getScheme() {
      return new RFileMetadataStandardScheme();
    }
  }

  private static class RFileMetadataStandardScheme extends StandardScheme<RFileMetadata> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RFileMetadata struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // FILENAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.filename = iprot.readString();
              struct.setFilenameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CREATED
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.created = iprot.readI64();
              struct.setCreatedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // UPDATED
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.updated = iprot.readI64();
              struct.setUpdatedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // DELETED
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.deleted = iprot.readI64();
              struct.setDeletedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // VERSION
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.version = iprot.readI32();
              struct.setVersionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // OWNER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.owner = iprot.readString();
              struct.setOwnerIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // CONTENT_LENGTH
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.contentLength = iprot.readI32();
              struct.setContentLengthIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // CONTENT_HASH
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.contentHash = iprot.readString();
              struct.setContentHashIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, RFileMetadata struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.filename != null) {
        if (struct.isSetFilename()) {
          oprot.writeFieldBegin(FILENAME_FIELD_DESC);
          oprot.writeString(struct.filename);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetCreated()) {
        oprot.writeFieldBegin(CREATED_FIELD_DESC);
        oprot.writeI64(struct.created);
        oprot.writeFieldEnd();
      }
      if (struct.isSetUpdated()) {
        oprot.writeFieldBegin(UPDATED_FIELD_DESC);
        oprot.writeI64(struct.updated);
        oprot.writeFieldEnd();
      }
      if (struct.isSetDeleted()) {
        oprot.writeFieldBegin(DELETED_FIELD_DESC);
        oprot.writeI64(struct.deleted);
        oprot.writeFieldEnd();
      }
      if (struct.isSetVersion()) {
        oprot.writeFieldBegin(VERSION_FIELD_DESC);
        oprot.writeI32(struct.version);
        oprot.writeFieldEnd();
      }
      if (struct.owner != null) {
        if (struct.isSetOwner()) {
          oprot.writeFieldBegin(OWNER_FIELD_DESC);
          oprot.writeString(struct.owner);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetContentLength()) {
        oprot.writeFieldBegin(CONTENT_LENGTH_FIELD_DESC);
        oprot.writeI32(struct.contentLength);
        oprot.writeFieldEnd();
      }
      if (struct.contentHash != null) {
        if (struct.isSetContentHash()) {
          oprot.writeFieldBegin(CONTENT_HASH_FIELD_DESC);
          oprot.writeString(struct.contentHash);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RFileMetadataTupleSchemeFactory implements SchemeFactory {
    public RFileMetadataTupleScheme getScheme() {
      return new RFileMetadataTupleScheme();
    }
  }

  private static class RFileMetadataTupleScheme extends TupleScheme<RFileMetadata> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RFileMetadata struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetFilename()) {
        optionals.set(0);
      }
      if (struct.isSetCreated()) {
        optionals.set(1);
      }
      if (struct.isSetUpdated()) {
        optionals.set(2);
      }
      if (struct.isSetDeleted()) {
        optionals.set(3);
      }
      if (struct.isSetVersion()) {
        optionals.set(4);
      }
      if (struct.isSetOwner()) {
        optionals.set(5);
      }
      if (struct.isSetContentLength()) {
        optionals.set(6);
      }
      if (struct.isSetContentHash()) {
        optionals.set(7);
      }
      oprot.writeBitSet(optionals, 8);
      if (struct.isSetFilename()) {
        oprot.writeString(struct.filename);
      }
      if (struct.isSetCreated()) {
        oprot.writeI64(struct.created);
      }
      if (struct.isSetUpdated()) {
        oprot.writeI64(struct.updated);
      }
      if (struct.isSetDeleted()) {
        oprot.writeI64(struct.deleted);
      }
      if (struct.isSetVersion()) {
        oprot.writeI32(struct.version);
      }
      if (struct.isSetOwner()) {
        oprot.writeString(struct.owner);
      }
      if (struct.isSetContentLength()) {
        oprot.writeI32(struct.contentLength);
      }
      if (struct.isSetContentHash()) {
        oprot.writeString(struct.contentHash);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RFileMetadata struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(8);
      if (incoming.get(0)) {
        struct.filename = iprot.readString();
        struct.setFilenameIsSet(true);
      }
      if (incoming.get(1)) {
        struct.created = iprot.readI64();
        struct.setCreatedIsSet(true);
      }
      if (incoming.get(2)) {
        struct.updated = iprot.readI64();
        struct.setUpdatedIsSet(true);
      }
      if (incoming.get(3)) {
        struct.deleted = iprot.readI64();
        struct.setDeletedIsSet(true);
      }
      if (incoming.get(4)) {
        struct.version = iprot.readI32();
        struct.setVersionIsSet(true);
      }
      if (incoming.get(5)) {
        struct.owner = iprot.readString();
        struct.setOwnerIsSet(true);
      }
      if (incoming.get(6)) {
        struct.contentLength = iprot.readI32();
        struct.setContentLengthIsSet(true);
      }
      if (incoming.get(7)) {
        struct.contentHash = iprot.readString();
        struct.setContentHashIsSet(true);
      }
    }
  }

}

