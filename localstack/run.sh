---https://stackoverflow.com/questions/44547574/create-api-gateway-in-localstack
API_NAME=api
REGION=eu-central-1
STAGE=test

awslocal apigateway create-rest-api \
    --region ${REGION} \
    --name ${API_NAME}
    --endpoint-url=http://localhost:8080


[ $? == 0 ] || fail 2 "Failed: AWS / apigateway / create-rest-api"

API_ID=$(awslocal apigateway get-rest-apis --query "items[?name==\`${API_NAME}\`].id" --output text --region ${REGION})

PARENT_RESOURCE_ID=$(awslocal apigateway get-resources --rest-api-id ${API_ID} --query 'items[?path==`/`].id' --output text --region ${REGION})

awslocal apigateway create-resource \
    --region ${REGION} \
    --rest-api-id ${API_ID} \
    --parent-id ${PARENT_RESOURCE_ID} \
    --path-part "savings/A"

[ $? == 0 ] || fail 3 "Failed: AWS / apigateway / create-resource"

RESOURCE_ID_1=$(awslocal apigateway get-resources --rest-api-id ${API_ID} --query 'items[?path==`/savings/A`].id' --output text --region ${REGION})

awslocal apigateway create-resource \
    --region ${REGION} \
    --rest-api-id ${API_ID} \
    --parent-id ${PARENT_RESOURCE_ID} \
    --path-part "savings/B"

[ $? == 0 ] || fail 3 "Failed: AWS / apigateway / create-resource"

RESOURCE_ID_2=$(awslocal apigateway get-resources --rest-api-id ${API_ID} --query 'items[?path==`/savings/B`].id' --output text --region ${REGION})


awslocal apigateway put-method \
    --region ${REGION} \
    --rest-api-id ${API_ID} \
    --resource-id ${RESOURCE_ID_1} \
    --http-method GET \
    --authorization-type "NONE" \

[ $? == 0 ] || fail 4 "Failed: AWS / apigateway / put-method"

awslocal apigateway put-method \
    --region ${REGION} \
    --rest-api-id ${API_ID} \
    --resource-id ${RESOURCE_ID_2} \
    --http-method GET \
    --authorization-type "NONE" \

[ $? == 0 ] || fail 4 "Failed: AWS / apigateway / put-method"

awslocal apigateway put-method \
    --region ${REGION} \
    --rest-api-id ${API_ID} \
    --resource-id ${RESOURCE_ID_1} \
    --http-method POST \
    --authorization-type "NONE" \

[ $? == 0 ] || fail 4 "Failed: AWS / apigateway / put-method"

awslocal apigateway put-method \
    --region ${REGION} \
    --rest-api-id ${API_ID} \
    --resource-id ${RESOURCE_ID_2} \
    --http-method POST \
    --authorization-type "NONE" \

[ $? == 0 ] || fail 4 "Failed: AWS / apigateway / put-method"

aws apigateway put-method-response --rest-api-id ${API_ID} \
       --resource-id ${RESOURCE_ID_2} --http-method GET \
       --status-code 200  --region  ${REGION}
  aws apigateway put-method-response --rest-api-id ${API_ID} \
      --resource-id ${RESOURCE_ID_1} --http-method GET \
      --status-code 200  --region  ${REGION}

aws apigateway put-method-response --rest-api-id ${API_ID} \
       --resource-id ${RESOURCE_ID_2} --http-method POST \
       --status-code 200  --region  ${REGION}
  aws apigateway put-method-response --rest-api-id ${API_ID} \
      --resource-id ${RESOURCE_ID_1} --http-method POST \
      --status-code 200  --region  ${REGION}



awslocal apigateway put-integration \
    --region ${REGION} \
    --rest-api-id ${API_ID} \
    --resource-id ${RESOURCE_ID_1} \
    --http-method GET \
    --type HTTP \
    --integration-http-method GET \
    --timeout-in-millis 5000 \
    --uri https://localhst:8081/balance \


[ $? == 0 ] || fail 5 "Failed: AWS / apigateway / put-integration"

awslocal apigateway put-integration \
    --region ${REGION} \
    --rest-api-id ${API_ID} \
    --resource-id ${RESOURCE_ID_2} \
    --http-method GET \
    --type HTTP \
    --integration-http-method GET \
    --timeout-in-millis 5000 \
    --uri https://localhst:8082/balance \


[ $? == 0 ] || fail 5 "Failed: AWS / apigateway / put-integration"


awslocal apigateway put-integration \
    --region ${REGION} \
    --rest-api-id ${API_ID} \
    --resource-id ${RESOURCE_ID_1} \
    --http-method POST \
    --type HTTP \
    --integration-http-method POST \
    --timeout-in-millis 5000 \
    --uri https://localhst:8081/balance \


[ $? == 0 ] || fail 5 "Failed: AWS / apigateway / put-integration"

awslocal apigateway put-integration \
    --region ${REGION} \
    --rest-api-id ${API_ID} \
    --resource-id ${RESOURCE_ID_2} \
    --http-method POST \
    --type HTTP \
    --integration-http-method POST \
    --timeout-in-millis 5000 \
    --uri https://localhst:8082/balance \


[ $? == 0 ] || fail 5 "Failed: AWS / apigateway / put-integration"


awslocal apigateway put-integration-response  --rest-api-id ${API_ID}  \
        --resource-id ${RESOURCE_ID_2} --http-method GET \
       --status-code 200 --selection-pattern ""  \
        --region ${REGION}
awslocal apigateway put-integration-response --rest-api-id ${API_ID}\
        --resource-id ${RESOURCE_ID_2}  --http-method GET \
       --status-code 200 --selection-pattern ""  \
        --region ${REGION}


awslocal apigateway put-integration-response  --rest-api-id ${API_ID}  \
        --resource-id ${RESOURCE_ID_2} --http-method POST \
       --status-code 200 --selection-pattern ""  \
        --region ${REGION}
awslocal apigateway put-integration-response --rest-api-id ${API_ID}\
        --resource-id ${RESOURCE_ID_2}  --http-method POST \
       --status-code 200 --selection-pattern ""  \
        --region ${REGION}

awslocal apigateway create-deployment \
    --region ${REGION} \
    --rest-api-id ${API_ID} \
    --stage-name ${STAGE} \

[ $? == 0 ] || fail 6 "Failed: AWS / apigateway / create-deployment"

