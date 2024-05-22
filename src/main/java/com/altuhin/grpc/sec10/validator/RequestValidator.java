package com.altuhin.grpc.sec10.validator;

import com.altuhin.models.sec10.ErrorMessage;
import com.altuhin.models.sec10.ValidationCode;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.ProtoUtils;

import java.util.Optional;

public class RequestValidator {

    private static final Metadata.Key<ErrorMessage> ERROR_MESSAGE_KEY = ProtoUtils.keyForProto(ErrorMessage.getDefaultInstance());
    /**
     * account number should be between 1 and 10.
     */
    public static Optional<StatusRuntimeException> validateAccount(int accountNumber) {
        if (accountNumber > 0 && accountNumber < 11) {
            return Optional.empty();
        }
        Metadata metadata = toMetadata(ValidationCode.INVALID_ACCOUNT);
        return Optional.of(Status.INVALID_ARGUMENT.withDescription("account number should be b/w 1 and 10.")
                .asRuntimeException(metadata));
    }

    /**
     * amount should be 10 multiplies.
     */
    public static Optional<StatusRuntimeException> isAmountDivisibleBy10(int amount) {
        if (amount > 0 && amount % 10 == 0) {
            return Optional.empty();
        }
        Metadata metadata = toMetadata(ValidationCode.INVALID_AMOUNT);
        return Optional.of(Status.FAILED_PRECONDITION.withDescription("requested amount should be 10 multiples.")
                .asRuntimeException(metadata));
    }

    /**
     * account should have enough balance.
     */
    public static Optional<StatusRuntimeException> hasSufficientBalance(int amount, int balance) {
        if (amount <= balance) {
            return Optional.empty();
        }
        Metadata metadata = toMetadata(ValidationCode.INSUFFICIENT_BALANCE);
        return Optional.of(Status.FAILED_PRECONDITION.withDescription("insufficient balance.").asRuntimeException(metadata));
    }

    private static Metadata toMetadata(ValidationCode validationCode) {
        Metadata metadata = new Metadata();
        ErrorMessage errorMessage = ErrorMessage.newBuilder().setValidationCode(validationCode).build();
        metadata.put(ERROR_MESSAGE_KEY, errorMessage);
        return metadata;
    }
}
