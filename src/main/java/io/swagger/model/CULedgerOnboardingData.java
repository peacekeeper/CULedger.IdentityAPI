package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.CULedgerCredentialMyCUIDBasic;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CULedgerOnboardingData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-03-02T19:50:39.116Z[GMT]")
public class CULedgerOnboardingData   {
  @JsonProperty("memberId")
  private String memberId = null;

  @JsonProperty("phoneNumber")
  private String phoneNumber = null;

  @JsonProperty("emailAddress")
  private String emailAddress = null;

  @JsonProperty("displayTextFromFI")
  private String displayTextFromFI = null;

  @JsonProperty("credentialData")
  private CULedgerCredentialMyCUIDBasic credentialData = null;

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

  public CULedgerOnboardingData phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * used internally, not part of Credential
   * @return phoneNumber
  **/
  @ApiModelProperty(example = "6135551212", value = "used internally, not part of Credential")

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public CULedgerOnboardingData emailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

  /**
   * used internally, not part of Credential
   * @return emailAddress
  **/
  @ApiModelProperty(example = "bubba@thecu.com", value = "used internally, not part of Credential")

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public CULedgerOnboardingData displayTextFromFI(String displayTextFromFI) {
    this.displayTextFromFI = displayTextFromFI;
    return this;
  }

  /**
   * TODO confirm this is linked at Connection level.
   * @return displayTextFromFI
  **/
  @ApiModelProperty(example = "New Loans and Accounts: 602-555-1212 Service: 603-555-1212 Routing # 123456789", value = "TODO confirm this is linked at Connection level.")

  public String getDisplayTextFromFI() {
    return displayTextFromFI;
  }

  public void setDisplayTextFromFI(String displayTextFromFI) {
    this.displayTextFromFI = displayTextFromFI;
  }

  public CULedgerOnboardingData credentialData(CULedgerCredentialMyCUIDBasic credentialData) {
    this.credentialData = credentialData;
    return this;
  }

  /**
   * Get credentialData
   * @return credentialData
  **/
  @ApiModelProperty(value = "")

  @Valid
  public CULedgerCredentialMyCUIDBasic getCredentialData() {
    return credentialData;
  }

  public void setCredentialData(CULedgerCredentialMyCUIDBasic credentialData) {
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
        Objects.equals(this.phoneNumber, cuLedgerOnboardingData.phoneNumber) &&
        Objects.equals(this.emailAddress, cuLedgerOnboardingData.emailAddress) &&
        Objects.equals(this.displayTextFromFI, cuLedgerOnboardingData.displayTextFromFI) &&
        Objects.equals(this.credentialData, cuLedgerOnboardingData.credentialData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(memberId, phoneNumber, emailAddress, displayTextFromFI, credentialData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CULedgerOnboardingData {\n");
    
    sb.append("    memberId: ").append(toIndentedString(memberId)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    emailAddress: ").append(toIndentedString(emailAddress)).append("\n");
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
