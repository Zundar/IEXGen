package objects.orders

import objects.AbstractOrderObject
import java.util.*
import java.util.Calendar
import java.text.SimpleDateFormat

open class NonTradingOrder(
        clientCode: String,
        docComment: String = UUID.randomUUID().toString(),
        extID: String,
        expireDay: Int,
        classCode: String,
        firm: String,
        action: Int
) : AbstractOrderObject(classCode, firm, action) {
    init {
        if (clientCode.length <= 20) {
            fields["CLIENT_CODE"] = clientCode
        } else {
            throw IllegalArgumentException("CLIENT_CODE больше 20 символов")
        }

        if (docComment.isEmpty() || docComment == "") {
            fields["DOC_COMMENT"] = UUID.randomUUID().toString()
        } else {
            if (extID.length <= 250) {
                fields["DOC_COMMENT"] = "$docComment ${UUID.randomUUID()}"
            } else {
                throw IllegalArgumentException("DOC_COMMENT больше 250 символов")
            }
        }

        if (extID.length <= 20) {
            fields["EXTID"] = extID
        } else {
            throw IllegalArgumentException("EXTID больше 20 символов")
        }

        if (expireDay != 0) {
            val dateFormat = SimpleDateFormat("yyyyMMdd")
            val calendar = Calendar.getInstance()
            calendar.time = Date()
            calendar.add(Calendar.DATE, expireDay)
            val currentDatePlusOne = calendar.time
            fields["EXPIRE_DAY"] = dateFormat.format(currentDatePlusOne)
        } else {
            fields["EXPIRE_DAY"] = 0
        }
    }
}