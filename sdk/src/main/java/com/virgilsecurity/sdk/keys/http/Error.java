package com.virgilsecurity.sdk.keys.http;

import android.support.annotation.Nullable;

import com.squareup.okhttp.ResponseBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Andrii Nutskovskyi on 12.10.15.
 */
public class Error {
    int errorCode;
    int responseCode;
    String message;
    Throwable t;

    public Error(Throwable t) {
        this.t = t;
        message = t.getMessage();
    }

    public Error(int responseCode, ResponseBody errorBody) {
        errorCode = 0;
        this.responseCode = responseCode;
        String response = null;
        JSONObject errorJson = null;
        try {
            response = errorBody.string();
            errorJson = new JSONObject(response);
            errorCode = errorJson.getJSONObject("error").getInt("code");
        } catch (JSONException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (errorCode) {
            case 10000:
                message = "The error code returned to the user in the case of some internal error that must not be specified to client";
                break;
            case 10100:
                message = "JSON specified as a request is invalid";
                break;
            case 10200:
                message = "The request_sign_uuid parameter was already used for another request";
                break;
            case 10201:
                message = "The request_sign_uuid parameter is invalid";
                break;
            case 10202:
                message = "The request sign header not found";
                break;
            case 10203:
                message = "The Public Key header not specified or incorrect";
                break;
            case 10204:
                message = "The request sign specified is incorrect";
                break;
            case 10207:
                message = "The Public Key UUID passed in header was not confirmed yet";
                break;
            case 10209:
                message = "Public Key specified in authorization header is registered for another application";
                break;
            case 10210:
                message = "Public Key value in request body for POST /public-key endpoint must be base64 encoded value";
                break;
            case 10211:
                message = "Public Key UUIDs in URL part and X-VIRGIL-REQUEST-SIGN-PK-ID header must match";
                break;
            case 10205:
                message = "The Virgil application token not specified or invalid";
                break;
            case 10206:
                message = "The Virgil statistics application error";
                break;
            case 10208:
                message = "Public Key value required in request body";
                break;
            case 20000:
                message = "Account object not found for id specified";
                break;
            case 20010:
                message = "Action token is invalid, expired on not found";
                break;
            case 20011:
                message = "Action token's confirmation codes number doesn't match";
                break;
            case 20012:
                message = "One of action token's confirmation codes is invalid";
                break;
            case 20100:
                message = "Public Key object not found for id specified";
                break;
            case 20101:
                message = "Public key length invalid";
                break;
            case 20102:
                message = "Public key not specified";
                break;
            case 20103:
                message = "Public key must be base64-encoded string";
                break;
            case 20104:
                message = "Public key must contain confirmed UserData entities";
                break;
            case 20105:
                message = "Public key must contain at least one \"UserData\" entry";
                break;
            case 20107:
                message = "There is UDID registered for current application already";
                break;
            case 20108:
                message = "UDIDs specified are registered for several accounts";
                break;
            case 20110:
                message = "Public key is not found for any application";
                break;
            case 20111:
                message = "Public key is found for another application";
                break;
            case 20112:
                message = "Public key is registered for another application";
                break;
            case 20113:
                message = "Sign verification failed for request UUID parameter in PUT /public-key";
                break;
            case 20200:
                message = "User Data object not found for id specified";
                break;
            case 20202:
                message = "User Data type specified as user identity is invalid";
                break;
            case 20203:
                message = "Domain value specified for the domain identity is invalid";
                break;
            case 20204:
                message = "Email value specified for the email identity is invalid";
                break;
            case 20205:
                message = "Phone value specified for the phone identity is invalid";
                break;
            case 20210:
                message = "User Data integrity constraint violation";
                break;
            case 20211:
                message = "User Data confirmation entity not found";
                break;
            case 20212:
                message = "User Data confirmation token invalid";
                break;
            case 20213:
                message = "User Data was already confirmed and does not need further confirmation";
                break;
            case 20214:
                message = "User Data class specified is invalid";
                break;
            case 20215:
                message = "Domain value specified for the domain identity is invalid";
                break;
            case 20216:
                message = "This user id had been confirmed earlier";
                break;
            case 20217:
                message = "The user data is not confirmed yet";
                break;
            case 20218:
                message = "The user data value is required";
                break;
            case 20300:
                message = "User info data validation failed";
                break;
            case 0: {
                switch (responseCode) {
                    case 400:
                        message = "Request error";
                        break;
                    case 401:
                        message = "Authorization error";
                        break;
                    case 404:
                        message = "Entity not found";
                        break;
                    case 405:
                        message = "Method not allowed";
                        break;
                    case 500:
                        message = "Internal Server error";
                        break;
                    default:
                        message = String.format("Undefined exception: %1$s; Http status: %2$s", response, responseCode);
                }
                break;
            }

            case 10001:
                message = "Internal application error.Route was not found.";
                break;
            case 10002:
                message = "Internal application error.Route not allowed.";
                break;
            case 20001:
                message = "Password validation failed";
                break;
            case 20002:
                message = "User data validation failed";
                break;
            case 20003:
                message = "Container was not found";
                break;
            case 20004:
                message = "Token validation failed";
                break;
            case 20005:
                message = "Token not found";
                break;
            case 20006:
                message = "Token has expired";
                break;
            case 30001:
                message = "Request Sign validation failed";
                break;
            case 40001:
                message = "Container validation failed";
                break;
            case 40002:
                message = "Container was not found";
                break;
            case 40003:
                message = "Container already exists";
                break;
            case 40004:
                message = "Container password was not specified";
                break;
            case 40005:
                message = "Container password validation failed";
                break;
            case 40006:
                message = "Container was not found in PKI service";
                break;
            case 40007:
                message = "Container type validation failed";
                break;
            case 50001:
                message = "Public Key ID validation failed";
                break;
            case 50002:
                message = "Public Key ID was not found";
                break;
            case 50003:
                message = "Public Key ID already exists";
                break;
            case 50004:
                message = "Private key validation failed";
                break;
            case 50005:
                message = "Private key base64 validation failed";
                break;
            case 60001:
                message = "Token was not found in request";
                break;
            case 60002:
                message = "User Data validation failed";
                break;
            case 60003:
                message = "Container was not found";
                break;
            case 60004:
                message = "Verification token hash expired";
                break;
            case 80001:
                message = "Request parameter validation failed";
                break;
            case 80002:
                message = "Has already used in another call. Please generate another one.";
                break;
            default:
                message = String.format("Undefined exception: %1$s; Http status: %2$s", response, responseCode);
        }
    }

    @Nullable
    public Throwable getThrowable() {
        return t;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
