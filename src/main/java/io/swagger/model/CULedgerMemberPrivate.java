package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.CULedgerDDO;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CULedgerMemberPrivate
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-03-01T11:48:59.751Z[GMT]")
public class CULedgerMemberPrivate   {
  @JsonProperty("memberPrivateDID")
  private String memberPrivateDID = null;

  @JsonProperty("memberId")
  private String memberId = null;

  @JsonProperty("memberDDO")
  private CULedgerDDO memberDDO = null;

  @JsonProperty("institutionPrivateKey")
  private String institutionPrivateKey = null;

  /**
   * Gets or Sets memberStatus
   */
  public enum MemberStatusEnum {
    NEW("new"),
    
    ONBOARDED("onboarded"),
    
    ONBOARDING("onboarding"),
    
    REVOKED("revoked"),
    
    UNKNOWN("unknown");

    private String value;

    MemberStatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static MemberStatusEnum fromValue(String text) {
      for (MemberStatusEnum b : MemberStatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("memberStatus")
  private MemberStatusEnum memberStatus = null;

  public CULedgerMemberPrivate memberPrivateDID(String memberPrivateDID) {
    this.memberPrivateDID = memberPrivateDID;
    return this;
  }

  /**
   * Get memberPrivateDID
   * @return memberPrivateDID
  **/
  @ApiModelProperty(example = "fake-DID-here", required = true, value = "")
  @NotNull

  public String getMemberPrivateDID() {
    return memberPrivateDID;
  }

  public void setMemberPrivateDID(String memberPrivateDID) {
    this.memberPrivateDID = memberPrivateDID;
  }

  public CULedgerMemberPrivate memberId(String memberId) {
    this.memberId = memberId;
    return this;
  }

  /**
   * If provided by FI this is the Member's Identifier in the external system. Otherwise it is system generated. See CONFIG_DETAIL.ID_TYPE
   * @return memberId
  **/
  @ApiModelProperty(example = "42", required = true, value = "If provided by FI this is the Member's Identifier in the external system. Otherwise it is system generated. See CONFIG_DETAIL.ID_TYPE")
  @NotNull

  public String getMemberId() {
    return memberId;
  }

  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }

  public CULedgerMemberPrivate memberDDO(CULedgerDDO memberDDO) {
    this.memberDDO = memberDDO;
    return this;
  }

  /**
   * Get memberDDO
   * @return memberDDO
  **/
  @ApiModelProperty(value = "")

  @Valid
  public CULedgerDDO getMemberDDO() {
    return memberDDO;
  }

  public void setMemberDDO(CULedgerDDO memberDDO) {
    this.memberDDO = memberDDO;
  }

  public CULedgerMemberPrivate institutionPrivateKey(String institutionPrivateKey) {
    this.institutionPrivateKey = institutionPrivateKey;
    return this;
  }

  /**
   * Get institutionPrivateKey
   * @return institutionPrivateKey
  **/
  @ApiModelProperty(example = "DDDDSDAFSDFASDF", value = "")

  public String getInstitutionPrivateKey() {
    return institutionPrivateKey;
  }

  public void setInstitutionPrivateKey(String institutionPrivateKey) {
    this.institutionPrivateKey = institutionPrivateKey;
  }

  public CULedgerMemberPrivate memberStatus(MemberStatusEnum memberStatus) {
    this.memberStatus = memberStatus;
    return this;
  }

  /**
   * Get memberStatus
   * @return memberStatus
  **/
  @ApiModelProperty(example = "onboarded", value = "")

  public MemberStatusEnum getMemberStatus() {
    return memberStatus;
  }

  public void setMemberStatus(MemberStatusEnum memberStatus) {
    this.memberStatus = memberStatus;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CULedgerMemberPrivate cuLedgerMemberPrivate = (CULedgerMemberPrivate) o;
    return Objects.equals(this.memberPrivateDID, cuLedgerMemberPrivate.memberPrivateDID) &&
        Objects.equals(this.memberId, cuLedgerMemberPrivate.memberId) &&
        Objects.equals(this.memberDDO, cuLedgerMemberPrivate.memberDDO) &&
        Objects.equals(this.institutionPrivateKey, cuLedgerMemberPrivate.institutionPrivateKey) &&
        Objects.equals(this.memberStatus, cuLedgerMemberPrivate.memberStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(memberPrivateDID, memberId, memberDDO, institutionPrivateKey, memberStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CULedgerMemberPrivate {\n");
    
    sb.append("    memberPrivateDID: ").append(toIndentedString(memberPrivateDID)).append("\n");
    sb.append("    memberId: ").append(toIndentedString(memberId)).append("\n");
    sb.append("    memberDDO: ").append(toIndentedString(memberDDO)).append("\n");
    sb.append("    institutionPrivateKey: ").append(toIndentedString(institutionPrivateKey)).append("\n");
    sb.append("    memberStatus: ").append(toIndentedString(memberStatus)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
