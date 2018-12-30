package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CULedgerKeyPair
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-12-30T11:45:41.226Z[GMT]")

public class CULedgerKeyPair   {
  @JsonProperty("valueName")
  private String valueName = null;

  @JsonProperty("value")
  private String value = null;

  public CULedgerKeyPair valueName(String valueName) {
    this.valueName = valueName;
    return this;
  }

  /**
   * Get valueName
   * @return valueName
  **/
  @ApiModelProperty(value = "")


  public String getValueName() {
    return valueName;
  }

  public void setValueName(String valueName) {
    this.valueName = valueName;
  }

  public CULedgerKeyPair value(String value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
  **/
  @ApiModelProperty(value = "")


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CULedgerKeyPair cuLedgerKeyPair = (CULedgerKeyPair) o;
    return Objects.equals(this.valueName, cuLedgerKeyPair.valueName) &&
        Objects.equals(this.value, cuLedgerKeyPair.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(valueName, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CULedgerKeyPair {\n");
    
    sb.append("    valueName: ").append(toIndentedString(valueName)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

