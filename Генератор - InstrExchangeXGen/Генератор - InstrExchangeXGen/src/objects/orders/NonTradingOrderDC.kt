package objects.orders

import java.math.BigDecimal
import java.util.*

class NonTradingOrderDC(minimalVolume: Float,
                        maximumVolume: Float,
                        secCode: String,
                        clientCodeCP: String,
                        tradeSystem: String,
                        clientCode: String,
                        docComment: String,
                        extID: String,
                        expireDay: Int,
                        classCode: String,
                        firm: String,
                        action: Int) :
        NonTradingOrder(clientCode, docComment, extID, expireDay, classCode, firm, action) {
    init {
        fields["VOLUME"] = (minimalVolume + Random().nextDouble() * (maximumVolume - minimalVolume)).toBigDecimal().setScale(2, BigDecimal.ROUND_HALF_DOWN)
        fields["SECCODE"] = secCode
        if (clientCodeCP.length <= 12) {
            fields["CLIENT_CODE_CP"] = clientCodeCP
        } else {
            throw IllegalArgumentException("CLIENT_CODE_CP больше 12 символов")
        }
        if (tradeSystem.length <= 32) {
            fields["TS"] = tradeSystem
        } else {
            throw IllegalArgumentException("TS больше 32 символов")
        }
    }
}