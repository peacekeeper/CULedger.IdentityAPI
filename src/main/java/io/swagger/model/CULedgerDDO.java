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
 * CULedgerDDO
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-02-19T12:02:09.781Z[GMT]")
public class CULedgerDDO   {
  @JsonProperty("DID")
  private String DID = null;

  @JsonProperty("institutionPrivateKey")
  private String institutionPrivateKey = null;

  public CULedgerDDO DID(String DID) {
    this.DID = DID;
    return this;
  }

  /**
   * Get DID
   * @return DID
  **/
  @ApiModelProperty(example = "SsPVi4HpA8jJx7wcTqCEQ3", required = true, value = "")
  @NotNull

  public String getDID() {
    return DID;
  }

  public void setDID(String DID) {
    this.DID = DID;
  }

  public CULedgerDDO institutionPrivateKey(String institutionPrivateKey) {
    this.institutionPrivateKey = institutionPrivateKey;
    return this;
  }

  /**
   * Get institutionPrivateKey
   * @return institutionPrivateKey
  **/
  @ApiModelProperty(example = "F6mDd1Govefw7XEa5mRJPRLsEKkgUbSsieEhtdSWVZac", value = "")

  public String getInstitutionPrivateKey() {
    return institutionPrivateKey;
  }

  public void setInstitutionPrivateKey(String institutionPrivateKey) {
    this.institutionPrivateKey = institutionPrivateKey;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CULedgerDDO cuLedgerDDO = (CULedgerDDO) o;
    return Objects.equals(this.DID, cuLedgerDDO.DID) &&
        Objects.equals(this.institutionPrivateKey, cuLedgerDDO.institutionPrivateKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(DID, institutionPrivateKey);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CULedgerDDO {\n");
    
    sb.append("    DID: ").append(toIndentedString(DID)).append("\n");
    sb.append("    institutionPrivateKey: ").append(toIndentedString(institutionPrivateKey)).append("\n");
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
