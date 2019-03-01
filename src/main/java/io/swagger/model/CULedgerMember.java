package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CULedgerMember
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-03-01T11:48:59.751Z[GMT]")
public class CULedgerMember   {
  @JsonProperty("memberPrivateDID")
  private String memberPrivateDID = null;

  @JsonProperty("memberId")
  private String memberId = null;

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

  public CULedgerMember memberPrivateDID(String memberPrivateDID) {
    this.memberPrivateDID = memberPrivateDID;
    return this;
  }

  /**
   * Get memberPrivateDID
   * @return memberPrivateDID
  **/
  @ApiModelProperty(example = "fake-DID-here", value = "")

  public String getMemberPrivateDID() {
    return memberPrivateDID;
  }

  public void setMemberPrivateDID(String memberPrivateDID) {
    this.memberPrivateDID = memberPrivateDID;
  }

  public CULedgerMember memberId(String memberId) {
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

  public CULedgerMember memberStatus(MemberStatusEnum memberStatus) {
    this.memberStatus = memberStatus;
    return this;
  }

  /**
   * Get memberStatus
   * @return memberStatus
  **/
  @ApiModelProperty(example = "onboarded", required = true, value = "")
  @NotNull

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
    CULedgerMember cuLedgerMember = (CULedgerMember) o;
    return Objects.equals(this.memberPrivateDID, cuLedgerMember.memberPrivateDID) &&
        Objects.equals(this.memberId, cuLedgerMember.memberId) &&
        Objects.equals(this.memberStatus, cuLedgerMember.memberStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(memberPrivateDID, memberId, memberStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CULedgerMember {\n");
    
    sb.append("    memberPrivateDID: ").append(toIndentedString(memberPrivateDID)).append("\n");
    sb.append("    memberId: ").append(toIndentedString(memberId)).append("\n");
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
