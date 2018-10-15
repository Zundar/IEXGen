import me.tongfei.progressbar.ProgressBar
import objects.AbstractOrderObject
import objects.orders.NonTradingOrderCB
import objects.orders.NonTradingOrderDC
import java.io.File
import java.util.*

class InstrExchangeXDocument(val filename: String,
                             val folderPath: String,
                             val settings: Settings,
                             val progressBarReference: ProgressBar) : Runnable {
    val rows = ArrayList<AbstractOrderObject>()
    private fun generateRow(action: Int): AbstractOrderObject {
        when (action) {
            0 -> return NonTradingOrderDC(
                    minimalVolume = settings.minimalVolume,
                    maximumVolume = settings.maximumVolume,
                    firm = settings.firm,
                    extID = settings.extID,
                    expireDay = settings.expireDay,
                    classCode = settings.classCode,
                    action = settings.action,
                    clientCode = settings.clientCode,
                    tradeSystem = settings.tradeSystem,
                    clientCodeCP = settings.clientCodeCP,
                    secCode = settings.seccode,
                    docComment = "Ввод ДС")
            3 -> return NonTradingOrderDC(
                    minimalVolume = settings.minimalVolume,
                    maximumVolume = settings.maximumVolume,
                    firm = settings.firm,
                    extID = settings.extID,
                    expireDay = settings.expireDay,
                    classCode = settings.classCode,
                    action = settings.action,
                    clientCode = settings.clientCode,
                    tradeSystem = settings.tradeSystem,
                    clientCodeCP = settings.clientCodeCP,
                    secCode = settings.seccode,
                    docComment = "Перевод ДС")
            5 -> return NonTradingOrderDC(
                    minimalVolume = settings.minimalVolume,
                    maximumVolume = settings.maximumVolume,
                    firm = settings.firm,
                    extID = settings.extID,
                    expireDay = settings.expireDay,
                    classCode = settings.classCode,
                    action = settings.action,
                    clientCode = settings.clientCode,
                    tradeSystem = settings.tradeSystem,
                    clientCodeCP = settings.clientCodeCP,
                    secCode = settings.seccode,
                    docComment = "Вывод ДС")
            6 -> return NonTradingOrderDC(
                    minimalVolume = settings.minimalVolume,
                    maximumVolume = settings.maximumVolume,
                    firm = settings.firm,
                    extID = settings.extID,
                    expireDay = settings.expireDay,
                    classCode = settings.classCode,
                    action = settings.action,
                    clientCode = settings.clientCode,
                    tradeSystem = settings.tradeSystem,
                    clientCodeCP = settings.clientCodeCP,
                    secCode = settings.seccode,
                    docComment = "Вывод ДС доп")
            7 -> return NonTradingOrderDC(
                    minimalVolume = settings.minimalVolume,
                    maximumVolume = settings.maximumVolume,
                    firm = settings.firm,
                    extID = settings.extID,
                    expireDay = settings.expireDay,
                    classCode = settings.classCode,
                    action = settings.action,
                    clientCode = settings.clientCode,
                    tradeSystem = settings.tradeSystem,
                    clientCodeCP = settings.clientCodeCP,
                    secCode = settings.seccode,
                    docComment = "Вывод ДС остаток")
            8 -> return NonTradingOrderCB(
                    realSecCode = settings.realSecCode,
                    clientCode = settings.clientCode,
                    action = settings.action,
                    classCode = settings.classCode,
                    expireDay = settings.expireDay,
                    extID = settings.extID,
                    firm = settings.firm,
                    realFirm = settings.realFirm,
                    realClass = settings.realClass,
                    account = settings.account,
                    clientCodeCp = settings.clientCodeCP,
                    docComment = "Ввод ЦБ")
            9 -> return NonTradingOrderCB(
                    realSecCode = settings.realSecCode,
                    clientCode = settings.clientCode,
                    action = settings.action,
                    classCode = settings.classCode,
                    expireDay = settings.expireDay,
                    extID = settings.extID,
                    firm = settings.firm,
                    realFirm = settings.realFirm,
                    realClass = settings.realClass,
                    account = settings.account,
                    clientCodeCp = settings.clientCodeCP,
                    docComment = "Вывод ЦБ")
            101 -> return NonTradingOrderDC(
                    minimalVolume = settings.minimalVolume,
                    maximumVolume = settings.maximumVolume,
                    firm = settings.firm,
                    extID = settings.extID,
                    expireDay = settings.expireDay,
                    classCode = settings.classCode,
                    action = settings.action,
                    clientCode = settings.clientCode,
                    tradeSystem = settings.tradeSystem,
                    clientCodeCP = settings.clientCodeCP,
                    secCode = settings.seccode,
                    docComment = "Списание комиссий")
            102 -> return NonTradingOrderDC(
                    minimalVolume = settings.minimalVolume,
                    maximumVolume = settings.maximumVolume,
                    firm = settings.firm,
                    extID = settings.extID,
                    expireDay = settings.expireDay,
                    classCode = settings.classCode,
                    action = settings.action,
                    clientCode = settings.clientCode,
                    tradeSystem = settings.tradeSystem,
                    clientCodeCP = settings.clientCodeCP,
                    secCode = settings.seccode,
                    docComment = "Блокировка ДС на текущий день")
            else -> throw IllegalArgumentException("Неверное значение action ${settings.action}")
        }
    }

    private fun getFileString(): String {
        val stringBuilder = StringBuilder()
        for (row in rows){
            stringBuilder.append(row.toString() + "\n")
        }
        stringBuilder.setLength(stringBuilder.length - 1)
        return stringBuilder.toString()
    }

    override fun run() {
        val file = File("$folderPath$filename")
        file.createNewFile()
        for (rowNumber in 1..settings.numberOfRowsPerFiles) {
            rows.add(generateRow(settings.action))
            progressBarReference.step()
        }
        file.appendText(getFileString())
        progressBarReference.step()
    }
}