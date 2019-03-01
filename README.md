# CULedger.IdentityAPI

This is a work-in-progress implementation of the CULedger Identity API, which connects to Evernym/Sovrin.

Definition:

  * https://app.swaggerhub.com/apis/continuumloop/CULedger.Identity/0.1.0

Documentation:

  * https://culedger.s3.amazonaws.com/diagrams/earlyclassdiag.svg
  * https://culedger.s3.amazonaws.com/diagrams/OnboardingMember-CULedgerDetail.svg
  * https://culedger.s3.amazonaws.com/diagrams/OnboardingMember-EvernymDetail.svg

## Connect.Me via TestFlight ##

https://testflight.apple.com/join/ktV7uxc3


## How to build

	docker build -f ./docker/Dockerfile . -t culedger-identityapi

## How to configure

The above command can optionally be modified to pass parameters into the build process:

**Institution DID seed:**

	--build-arg VCX_INSTITUTION_DID_SEED="wuuw3iuchai3Bou0fae3voh3Iequeuwu"

**Genesis path:**

	--build-arg VCX_GENESIS_PATH="pool_transactions_sandbox_genesis"

**URL to the institution's logo:**

	--build-arg VCX_INSTITUTION_LOGO_URL="https://culedger.s3.amazonaws.com/Connect.Me/CULedger-ConnectMe-Logo-256x256.png"

**Institution name:**

	--build-arg VCX_INSTITUTION_NAME="CULedger"

**Credential name:**

	--build-arg VCX_CREDENTIAL_NAME="MyCUID"

**Credential value:**

	--build-arg VCX_CREDENTIAL_VALUE="Test CU"

**Timeouts:**

	--build-arg VCX_TIMEOUT_CONNECTIONINVITE="600"
	--build-arg VCX_TIMEOUT_CREDENTIALOFFER="300"
	--build-arg VCX_TIMEOUT_CREDENTIALSEND="120"
	--build-arg VCX_TIMEOUT_PROOFREQUEST="300"

**CULedger Demo Build**

	docker build -f ./docker/Dockerfile . -t culedger-identityapi --build-arg VCX_INSTITUTION_LOGO_URL="https://culedger.s3.amazonaws.com/Connect.Me/CULedger-ConnectMe-Logo-256x256.png" --build-arg VCX_INSTITUTION_NAME="CULedger" --build-arg VCX_CREDENTIAL_NAME="MyCUID" 


## How to run

	docker run -ti -p 8080:8080 culedger-identityapi:latest

## How to run with persistent volume

	docker run -ti -p 8080:8080 -v culedger-identityapi-state:/opt/culedger-identityapi-state culedger-identityapi:latest

## How to run with pre-existing schema ID and creddef ID

	docker run -ti -p 8080:8080 -e VCX_SCHEMA_ID=SsPVi4HpA8jJx7wcTqCEQ4:2:mycuid-315957:0.0.1 -e VCX_CREDDEF_ID=SsPVi4HpA8jJx7wcTqCEQ4:3:CL:30062:tag-461289 culedger-identityapi:latest

## How to run multiple containers on the same host machine

1. Make sure containers are mapped to different ports on the host machine (i.e. `-p 8080:8080`, `-p 8081:8080`, `-p 8082:8080`, etc.).

2. If using persistent volumes, make sure containers use different volumes (i.e. `-v culedger-identityapi-state1:/opt/culedger-identityapi-state`, `-v culedger-identityapi-state2:/opt/culedger-identityapi-state`, `-v culedger-identityapi-state3:/opt/culedger-identityapi-state`).

## How to test via Swagger UI

Open `http://localhost:8080/darrellodonnell/CULedger.Identity/0.1.0/swagger-ui.html` in your local web browser.

### How to onboard a member: (see onboard.sh for sample cURL command to run)

If the member ID is *ms7823* and their phone number is *+436643154848*:

**POST** call to `/member/ms7823/onboard`:

	{
	    "memberId": "ms7823",
	    "memberPhoneNumber": "+436643154848",
	    "memberEmail": "test@gmail.com",
	    "displayTextFromFI": "Let's get connected via MyCUID!",
	    "credentialData": {
	        "id": "UUID-GOES-HERE",
          "Institution": "CULedger Credit Union",
	        "memberId": "ms7823",
	        "status": "active",
	        "memberSince": null,
	        "credDataPayload": ""
	    }
	}

Response (after long wait time): HTTP 200 = Connection established and myCUID credential issued, HTTP 500 = Error

### How to authenticate a member: (see authenticate.sh for sample cURL command to run)

If the member ID is *ms7823*:

**PUT** call to `/member/ms7823/authenticate`

Response (after long wait time): HTTP 200 = Valid myCUID proof Received, HTTP 401 = Invalid proof Received, HTTP 404 = Member ID unknown (not onboarded), HTTP 500 = Error

## Testing Setup ##
* Install the ConnectMe app from link above
* Make sure to include phone number with ConnectMe app installed when performing API calls
* After, running onboard request, you will receive a text message that CULedger wants to connect. Click that link and follow the instructions in ConnectMe app.
* You must then accept the MyCUID offer before the API will return with completion status.

## How to test using API Client

From the Swagger API definition, create an API client in any language:

  * https://app.swaggerhub.com/apis/continuumloop/CULedger.Identity/0.1.0
