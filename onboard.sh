input_json=$(cat <<EOF
{
    "memberId": "ms7827",
    "phoneNumber": "+17703299814",
    "emailAddress": "tshelton@culedger.com",
    "displayTextFromFI": "Let's get connected via MyCUID!",
    "credentialData": {
        "CredentialId": "UUID-GOES-HERE",
        "Institution": "CULedger Credit Union",
        "Credential": null,
        "MemberNumber": "ms7827",
        "MemberSince": null
    }
}
EOF
)

curl -H "Content-Type: application/json" -X POST -d "$input_json" http://localhost:8080/CULedger/CULedger.Identity/0.2.0/member/ms7827/onboard
