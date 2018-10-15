package objects.cancel

import objects.AbstractOrderObject
import java.util.*

class NonTradingCancel(instrNo: String,
                       comment: String = UUID.randomUUID().toString(),
                       classCode: String,
                       firm: String,
                       action: Int) :
        AbstractOrderObject(classCode, firm, action) {
    init {
        fields["INSTR_NO"] = instrNo
        fields["COMMENT"] = comment
    }
}