# CULedger.IdentityAPI

This is a work-in-progress (not yet functional!) implementation of the CULedger Identity API, which connects to Evernym/Sovrin.

Definition:

  * https://app.swaggerhub.com/apis/continuumloop/CULedger.Identity/0.1.0

Documentation:

  * https://culedger.s3.amazonaws.com/diagrams/earlyclassdiag.svg
  * https://culedger.s3.amazonaws.com/diagrams/OnboardingMember-CULedgerDetail.svg
  * https://culedger.s3.amazonaws.com/diagrams/OnboardingMember-EvernymDetail.svg

## How to build

	docker build -f ./docker/Dockerfile . -t culedger-identityapi

## How to run

	docker run -ti -p 8080:8080 culedger-identityapi:latest

## How to test via Swagger UI

Open `http://localhost:8080/darrellodonnell/CULedger.Identity/0.1.0/swagger-ui.html` in your local web browser.

### How to onboard a member:

**POST** call to `/member/{memberId}/onboard`:

	{
	    "memberId": "ms7823",
	    "memberPhoneNumber": "+436643154848",
	    "memberEmail": "test@gmail.com",
	    "displayTextFromFI": "Welcome to MyCUID!",
	    "credentialData": {
	        "id": null,
	        "memberId": "ms7823",
	        "status": "active",
	        "memberSince": null,
	        "credDataPayload": ""
	    }
	}

Response: HTTP 200 = Connection established and myCUID credential issued, HTTP 500 = Error

### How to authenticate a member:

**PUT** call to `/member/{memberId}/authenticate`

Response: HTTP 200 = Valid Proof Received, HTTP 401 = Invalid Proof Received, HTTP 500 = Error

## How to test using API Client

From the Swagger API definition, create an API client in any language:

  * https://app.swaggerhub.com/apis/continuumloop/CULedger.Identity/0.1.0
