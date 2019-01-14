input_json=$(cat <<EOF
{
    "memberId": "ms7827",
    "memberPhoneNumber": "+17703299814",
    "memberEmail": "tshelton@culedger.com",
    "displayTextFromFI": "Let's get connected via MyCUID!",
    "credentialData": {
        "id": "UUID-GOES-HERE",
        "Institution": "CULedger Credit Union",
        "memberId": "ms7827",
        "status": "active",
        "memberSince": null,
        "credDataPayload": ""
    }
}
EOF
)

curl -H "Content-Type: application/json" -X POST -d "$input_json" http://localhost:8080/darrellodonnell/CULedger.Identity/0.1.0/member/ms7827/onboard
