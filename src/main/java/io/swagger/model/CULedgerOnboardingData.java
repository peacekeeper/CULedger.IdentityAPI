package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.CULedgerCredentialBasic;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CULedgerOnboardingData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-14T21:44:13.544Z[GMT]")

public class CULedgerOnboardingData   {
  @JsonProperty("memberId")
  private String memberId = null;

  @JsonProperty("memberPhoneNumber")
  private String memberPhoneNumber = null;

  @JsonProperty("memberEmail")
  private String memberEmail = null;

  @JsonProperty("displayTextFromFI")
  private String displayTextFromFI = null;

  @JsonProperty("credentialData")
  private CULedgerCredentialBasic credentialData = null;

  public CULedgerOnboardingData memberId(String memberId) {
    this.memberId = memberId;
    return this;
  }

  /**
   * Get memberId
   * @return memberId
  **/
  @ApiModelProperty(example = "lllll-ddddd--ddddd--sssss", required = true, value = "")
  @NotNull


  public String getMemberId() {
    return memberId;
  }

  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }

  public CULedgerOnboardingData memberPhoneNumber(String memberPhoneNumber) {
    this.memberPhoneNumber = memberPhoneNumber;
    return this;
  }

  /**
   * used internally, not part of Credential
   * @return memberPhoneNumber
  **/
  @ApiModelProperty(example = "6135551212", value = "used internally, not part of Credential")


  public String getMemberPhoneNumber() {
    return memberPhoneNumber;
  }

  public void setMemberPhoneNumber(String memberPhoneNumber) {
    this.memberPhoneNumber = memberPhoneNumber;
  }

  public CULedgerOnboardingData memberEmail(String memberEmail) {
    this.memberEmail = memberEmail;
    return this;
  }

  /**
   * used internally, not part of Credential
   * @return memberEmail
  **/
  @ApiModelProperty(example = "bubba@thecu.com", value = "used internally, not part of Credential")


  public String getMemberEmail() {
    return memberEmail;
  }

  public void setMemberEmail(String memberEmail) {
    this.memberEmail = memberEmail;
  }

  public CULedgerOnboardingData displayTextFromFI(String displayTextFromFI) {
    this.displayTextFromFI = displayTextFromFI;
    return this;
  }

  /**
   * Get displayTextFromFI
   * @return displayTextFromFI
  **/
  @ApiModelProperty(example = "New Loans and Accounts: 602-555-1212 Service: 603-555-1212 Routing # 123456789", value = "")


  public String getDisplayTextFromFI() {
    return displayTextFromFI;
  }

  public void setDisplayTextFromFI(String displayTextFromFI) {
    this.displayTextFromFI = displayTextFromFI;
  }

  public CULedgerOnboardingData credentialData(CULedgerCredentialBasic credentialData) {
    this.credentialData = credentialData;
    return this;
  }

  /**
   * Get credentialData
   * @return credentialData
  **/
  @ApiModelProperty(value = "")

  @Valid

  public CULedgerCredentialBasic getCredentialData() {
    return credentialData;
  }

  public void setCredentialData(CULedgerCredentialBasic credentialData) {
    this.credentialData = credentialData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CULedgerOnboardingData cuLedgerOnboardingData = (CULedgerOnboardingData) o;
    return Objects.equals(this.memberId, cuLedgerOnboardingData.memberId) &&
        Objects.equals(this.memberPhoneNumber, cuLedgerOnboardingData.memberPhoneNumber) &&
        Objects.equals(this.memberEmail, cuLedgerOnboardingData.memberEmail) &&
        Objects.equals(this.displayTextFromFI, cuLedgerOnboardingData.displayTextFromFI) &&
        Objects.equals(this.credentialData, cuLedgerOnboardingData.credentialData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(memberId, memberPhoneNumber, memberEmail, displayTextFromFI, credentialData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CULedgerOnboardingData {\n");
    
    sb.append("    memberId: ").append(toIndentedString(memberId)).append("\n");
    sb.append("    memberPhoneNumber: ").append(toIndentedString(memberPhoneNumber)).append("\n");
    sb.append("    memberEmail: ").append(toIndentedString(memberEmail)).append("\n");
    sb.append("    displayTextFromFI: ").append(toIndentedString(displayTextFromFI)).append("\n");
    sb.append("    credentialData: ").append(toIndentedString(credentialData)).append("\n");
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

