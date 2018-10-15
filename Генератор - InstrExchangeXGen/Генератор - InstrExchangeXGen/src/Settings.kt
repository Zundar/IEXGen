import org.pmw.tinylog.Configurator
import org.pmw.tinylog.Level
import org.pmw.tinylog.writers.FileWriter
import java.net.URI

class Settings(val threadPoolSize: Int,
               val logFile: URI,
               val numberOfFiles: Int,
               val numberOfRowsPerFiles:Int,
               val isDebug: Boolean,
               val resultDataFolder: String,
               val classCode: String,
               val firm: String,
               val action: Int,
               val clientCode: String,
               val minimalVolume: Float,
               val maximumVolume: Float,
               val quantity: Int,
               val seccode: String,
               val secCodeCP: String,
               val realSecCode: String,
               val realClass: String,
               val realFirm: String,
               val clientCodeCP: String,
               val tradeSystem: String,
               val account: String,
               val extID: String,
               val expireDay: Int,
               val fileMask:String,
               val fillZero:Int) {

    private val loggerFormat = "{date:yyyy-MM-dd HH:mm:ss} {message}"

    init {
        if (isDebug) {
            Configurator.defaultConfig()
                    .formatPattern(loggerFormat)
                    .writer(FileWriter(logFile.toString(), true, true))
                    .level(Level.DEBUG).activate()
        } else {
            Configurator.defaultConfig()
                    .formatPattern(loggerFormat)
                    .writer(FileWriter(logFile.toString(), true, true))
                    .level(Level.INFO).activate()
        }
    }

    override fun toString(): String {
        return "Settings(threadPoolSize=$threadPoolSize, logFile=$logFile, numberOfFiles=$numberOfFiles, numberOfRowsPerFiles=$numberOfRowsPerFiles, isDebug=$isDebug, resultDataFolder='$resultDataFolder', classCode='$classCode', firm='$firm', action=$action, clientCode='$clientCode', minimalVolume=$minimalVolume, maximumVolume=$maximumVolume, quantity=$quantity, seccode='$seccode', secCodeCP='$secCodeCP', realSecCode='$realSecCode', realClass='$realClass', realFirm='$realFirm', clientCodeCP='$clientCodeCP', tradeSystem='$tradeSystem', account='$account', extID='$extID', expireDay=$expireDay, loggerFormat='$loggerFormat')"
    }
}