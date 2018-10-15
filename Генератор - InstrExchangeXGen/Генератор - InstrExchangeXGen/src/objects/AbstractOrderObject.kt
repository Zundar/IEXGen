package objects

abstract class AbstractOrderObject(
        classCode: String,
        firm: String,
        action: Int) {
    val fields = HashMap<String, Any>()
    val separator = ";"

    init {
        fields["ACTION"] = action
        fields["CLASSCODE"] = classCode
        fields["FIRM"] = firm
    }

    override fun toString(): String {
        return fields.toString()
                .replace("{", "")
                .replace("}", "")
                .replace(", ", separator)
    }
}