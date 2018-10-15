package objects.orders

class NonTradingOrderCB(realSecCode: String,
                        realClass: String,
                        realFirm: String,
                        clientCodeCp: String,
                        account: String,
                        clientCode: String,
                        docComment: String,
                        extID: String,
                        expireDay: Int,
                        classCode: String,
                        firm: String,
                        action: Int) :
        NonTradingOrder(clientCode, docComment, extID, expireDay, classCode, firm, action) {
    init {
        fields["REAL_SECCODE"] = realSecCode
        fields["REAL_CLASS"] = realClass
        fields["REAL_FIRM"] = realFirm
        if (clientCodeCp.length <= 12) {
            fields["CLIENT_CODE_CP"] = clientCodeCp
        } else {
            throw IllegalArgumentException("CLIENT_CODE_CP больше 12 символов")
        }

        if (account.length <= 12) {
            fields["ACCOUNT"] = account
        } else {
            throw IllegalArgumentException("ACCOUNT больше 12 символов")
        }
    }
}