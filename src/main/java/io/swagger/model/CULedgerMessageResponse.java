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
 * CULedgerMessageResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-02-19T12:02:09.781Z[GMT]")
public class CULedgerMessageResponse   {
  @JsonProperty("messageId")
  private String messageId = null;

  /**
   * indicates the message state.
   */
  public enum StatusEnum {
    DENIED("denied"),
    
    INPROCESS("inProcess"),
    
    SIGNED("signed"),
    
    FAILURE("failure");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("status")
  private StatusEnum status = null;

  @JsonProperty("signedPayload")
  private String signedPayload = null;

  @JsonProperty("signature")
  private String signature = null;

  @JsonProperty("textResponse")
  private String textResponse = null;

  public CULedgerMessageResponse messageId(String messageId) {
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

  public CULedgerMessageResponse status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * indicates the message state.
   * @return status
  **/
  @ApiModelProperty(value = "indicates the message state.")

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public CULedgerMessageResponse signedPayload(String signedPayload) {
    this.signedPayload = signedPayload;
    return this;
  }

  /**
   * data that was signed.
   * @return signedPayload
  **/
  @ApiModelProperty(example = "msgid:31505c9f-db87-407f-9bfa-d42b0552e79a;msgSHA512: C793530778F173A958BBF8D12273F43567AEE997A49384C3F82A616314833E586CD69EA92501574E285CC71DCC665DC623717353023CD4B1CA3D159A5ACF296A;type:termsconditionsupdate;dateSent:2018-11-08-05:01", value = "data that was signed.")

  public String getSignedPayload() {
    return signedPayload;
  }

  public void setSignedPayload(String signedPayload) {
    this.signedPayload = signedPayload;
  }

  public CULedgerMessageResponse signature(String signature) {
    this.signature = signature;
    return this;
  }

  /**
   * digital signature of the message as received from Member.
   * @return signature
  **/
  @ApiModelProperty(example = "6E873585E7F889EEE8AC069A82335F16582CA462243D40F8A6163B3D4E56DD9857D34350955FC1973E5A35C9239E43FC7BF7CBEA4C0316136FE193CAB8A74B1F", value = "digital signature of the message as received from Member.")

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public CULedgerMessageResponse textResponse(String textResponse) {
    this.textResponse = textResponse;
    return this;
  }

  /**
   * text typed in by person in response to request. optional.
   * @return textResponse
  **/
  @ApiModelProperty(example = "DENIED - I don't know abou this transaction.", value = "text typed in by person in response to request. optional.")

  public String getTextResponse() {
    return textResponse;
  }

  public void setTextResponse(String textResponse) {
    this.textResponse = textResponse;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CULedgerMessageResponse cuLedgerMessageResponse = (CULedgerMessageResponse) o;
    return Objects.equals(this.messageId, cuLedgerMessageResponse.messageId) &&
        Objects.equals(this.status, cuLedgerMessageResponse.status) &&
        Objects.equals(this.signedPayload, cuLedgerMessageResponse.signedPayload) &&
        Objects.equals(this.signature, cuLedgerMessageResponse.signature) &&
        Objects.equals(this.textResponse, cuLedgerMessageResponse.textResponse);
  }

  @Override
  public int hashCode() {
    return Objects.hash(messageId, status, signedPayload, signature, textResponse);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CULedgerMessageResponse {\n");
    
    sb.append("    messageId: ").append(toIndentedString(messageId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    signedPayload: ").append(toIndentedString(signedPayload)).append("\n");
    sb.append("    signature: ").append(toIndentedString(signature)).append("\n");
    sb.append("    textResponse: ").append(toIndentedString(textResponse)).append("\n");
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
