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
 * CULedgerMessage
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-02-19T12:02:09.781Z[GMT]")
public class CULedgerMessage   {
  @JsonProperty("messageId")
  private String messageId = null;

  @JsonProperty("memberId")
  private String memberId = null;

  @JsonProperty("messageText")
  private String messageText = null;

  @JsonProperty("messageInstructions")
  private String messageInstructions = null;

  @JsonProperty("positiveButtonText")
  private String positiveButtonText = null;

  @JsonProperty("negativeButtonText")
  private String negativeButtonText = null;

  /**
   * Defines the types of signatures allowed:   1. none;   2. simple button press;   3. Biometric (e.g. Apple Touch ID, Apple Face ID, fingerprint ) 
   */
  public enum MessageSigningTypeEnum {
    NOSIGNATURE("noSignature"),
    
    BASICSIGNATURE("basicSignature"),
    
    BIOMETRICSIGNATURE("biometricSignature");

    private String value;

    MessageSigningTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static MessageSigningTypeEnum fromValue(String text) {
      for (MessageSigningTypeEnum b : MessageSigningTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("messageSigningType")
  private MessageSigningTypeEnum messageSigningType = null;

  @JsonProperty("messageSigningPayload")
  private String messageSigningPayload = null;

  public CULedgerMessage messageId(String messageId) {
    this.messageId = messageId;
    return this;
  }

  /**
   * identifier for message (generated outside of system)
   * @return messageId
  **/
  @ApiModelProperty(example = "31505c9f-db87-407f-9bfa-d42b0552e79a", required = true, value = "identifier for message (generated outside of system)")
  @NotNull

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public CULedgerMessage memberId(String memberId) {
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

  public CULedgerMessage messageText(String messageText) {
    this.messageText = messageText;
    return this;
  }

  /**
   * message that will be displayed to the user. (multi-line text)
   * @return messageText
  **/
  @ApiModelProperty(example = "A wire transfer has been queued for your acceptance. Can you please confirm the following details: $10,738 to be wired today to Bubba Ltd. in Spokane, WA.", required = true, value = "message that will be displayed to the user. (multi-line text)")
  @NotNull

  public String getMessageText() {
    return messageText;
  }

  public void setMessageText(String messageText) {
    this.messageText = messageText;
  }

  public CULedgerMessage messageInstructions(String messageInstructions) {
    this.messageInstructions = messageInstructions;
    return this;
  }

  /**
   * We need you Confirm or Deny the following request while you are speaking to Susan from the credit union. To Confirm, please press the Confirm button.
   * @return messageInstructions
  **/
  @ApiModelProperty(value = "We need you Confirm or Deny the following request while you are speaking to Susan from the credit union. To Confirm, please press the Confirm button.")

  public String getMessageInstructions() {
    return messageInstructions;
  }

  public void setMessageInstructions(String messageInstructions) {
    this.messageInstructions = messageInstructions;
  }

  public CULedgerMessage positiveButtonText(String positiveButtonText) {
    this.positiveButtonText = positiveButtonText;
    return this;
  }

  /**
   * The text that shows to a user when they want to take the POSITIVE action (accept / yes / approve). DECISION needed here - this gets into multi-lingual and cultural issues that may be best handled in Connect.Me?
   * @return positiveButtonText
  **/
  @ApiModelProperty(example = "Approve", value = "The text that shows to a user when they want to take the POSITIVE action (accept / yes / approve). DECISION needed here - this gets into multi-lingual and cultural issues that may be best handled in Connect.Me?")

  public String getPositiveButtonText() {
    return positiveButtonText;
  }

  public void setPositiveButtonText(String positiveButtonText) {
    this.positiveButtonText = positiveButtonText;
  }

  public CULedgerMessage negativeButtonText(String negativeButtonText) {
    this.negativeButtonText = negativeButtonText;
    return this;
  }

  /**
   * The text that shows to a user when they want to take the NEGATIVE action (cancel / no / deny). DECISION needed here - this gets into multi-lingual and cultural issues that may be best handled in Connect.Me?
   * @return negativeButtonText
  **/
  @ApiModelProperty(example = "Deny", value = "The text that shows to a user when they want to take the NEGATIVE action (cancel / no / deny). DECISION needed here - this gets into multi-lingual and cultural issues that may be best handled in Connect.Me?")

  public String getNegativeButtonText() {
    return negativeButtonText;
  }

  public void setNegativeButtonText(String negativeButtonText) {
    this.negativeButtonText = negativeButtonText;
  }

  public CULedgerMessage messageSigningType(MessageSigningTypeEnum messageSigningType) {
    this.messageSigningType = messageSigningType;
    return this;
  }

  /**
   * Defines the types of signatures allowed:   1. none;   2. simple button press;   3. Biometric (e.g. Apple Touch ID, Apple Face ID, fingerprint ) 
   * @return messageSigningType
  **/
  @ApiModelProperty(value = "Defines the types of signatures allowed:   1. none;   2. simple button press;   3. Biometric (e.g. Apple Touch ID, Apple Face ID, fingerprint ) ")

  public MessageSigningTypeEnum getMessageSigningType() {
    return messageSigningType;
  }

  public void setMessageSigningType(MessageSigningTypeEnum messageSigningType) {
    this.messageSigningType = messageSigningType;
  }

  public CULedgerMessage messageSigningPayload(String messageSigningPayload) {
    this.messageSigningPayload = messageSigningPayload;
    return this;
  }

  /**
   * data that will be signed.
   * @return messageSigningPayload
  **/
  @ApiModelProperty(example = "msgid:31505c9f-db87-407f-9bfa-d42b0552e79a;msgSHA512: C793530778F173A958BBF8D12273F43567AEE997A49384C3F82A616314833E586CD69EA92501574E285CC71DCC665DC623717353023CD4B1CA3D159A5ACF296A;type:termsconditionsupdate;dateSent:2018-11-08-05:01", value = "data that will be signed.")

  public String getMessageSigningPayload() {
    return messageSigningPayload;
  }

  public void setMessageSigningPayload(String messageSigningPayload) {
    this.messageSigningPayload = messageSigningPayload;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CULedgerMessage cuLedgerMessage = (CULedgerMessage) o;
    return Objects.equals(this.messageId, cuLedgerMessage.messageId) &&
        Objects.equals(this.memberId, cuLedgerMessage.memberId) &&
        Objects.equals(this.messageText, cuLedgerMessage.messageText) &&
        Objects.equals(this.messageInstructions, cuLedgerMessage.messageInstructions) &&
        Objects.equals(this.positiveButtonText, cuLedgerMessage.positiveButtonText) &&
        Objects.equals(this.negativeButtonText, cuLedgerMessage.negativeButtonText) &&
        Objects.equals(this.messageSigningType, cuLedgerMessage.messageSigningType) &&
        Objects.equals(this.messageSigningPayload, cuLedgerMessage.messageSigningPayload);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messageId, memberId, messageText, messageInstructions, positiveButtonText, negativeButtonText, messageSigningType, messageSigningPayload);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CULedgerMessage {\n");
    
    sb.append("    messageId: ").append(toIndentedString(messageId)).append("\n");
    sb.append("    memberId: ").append(toIndentedString(memberId)).append("\n");
    sb.append("    messageText: ").append(toIndentedString(messageText)).append("\n");
    sb.append("    messageInstructions: ").append(toIndentedString(messageInstructions)).append("\n");
    sb.append("    positiveButtonText: ").append(toIndentedString(positiveButtonText)).append("\n");
    sb.append("    negativeButtonText: ").append(toIndentedString(negativeButtonText)).append("\n");
    sb.append("    messageSigningType: ").append(toIndentedString(messageSigningType)).append("\n");
    sb.append("    messageSigningPayload: ").append(toIndentedString(messageSigningPayload)).append("\n");
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
