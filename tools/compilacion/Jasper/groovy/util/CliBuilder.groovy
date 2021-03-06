package groovy.util

import org.apache.commons.cli.*
import org.codehaus.groovy.runtime.InvokerHelper


/**
    Supported Option Properties:
    argName:        String
    longOpt:        String
    args:           int
    optionalArg:    boolean
    required:       boolean
    type:           Object
    valueSeparator: char

    @see org.apache.commons.cli.Option for meaning of the parameters.
    @see CliBuilderTest for example usages.
    @author Dierk Koenig
*/

class CliBuilder {

    // default settings: use setters to override
    String usage             = 'groovy'
    CommandLineParser parser = new PosixParser()
    HelpFormatter formatter  = new HelpFormatter()
    PrintWriter writer       = new PrintWriter(System.out)

    Options options          = new Options()

    /**
        Recognize all one-character method calls as option specifications.
    */
    def invokeMethod(String name, Object args){
        if (1 == name.size()) {
            options.addOption(option(name, args[0], args[1]))
            return null
        }
        return InvokerHelper.getMetaClass(this).invokeMethod(this, name, args)
    }

    /**
        Make options accessible from command line args with parser (default: Posix).
        Returns null on bad command lines.
    */
    OptionAccessor parse(args) {
        try {
            return new OptionAccessor( parser.parse(options, args as String[], true) )
        } catch (ParseException pe) {
            writer.println("error: " + pe.getMessage())
            usage()
            return null
        }
    }

    /**
        Print the usage message with writer (default: System.out) and formatter (default: HelpFormatter)
    */
    void usage(){
        formatter.printHelp(writer, formatter.defaultWidth, usage, '', options, formatter.defaultLeftPad, formatter.defaultDescPad, '')
        writer.flush()
    }

    // implementation details -------------------------------------

    /**
        How to create an option from the specification.
    */
    Option option (shortname, Map details, info){
        Option option = new Option( shortname, info)
        details.each { key, value ->
            if (key == 'optionalArg')       // surprising that this extra handling is needed
                option.setOptionalArg(value)
            else
                option[key] = value
        }
        return option
    }
}

class OptionAccessor {
    CommandLine inner
    OptionAccessor(CommandLine inner){
        this.inner = inner
    }
    def invokeMethod(String name, Object args){
        return InvokerHelper.getMetaClass(inner).invokeMethod(inner, name, args)
    }
    def getProperty(String name) {
        def methodname = 'getOptionValue'
        if (name.size() > 1 && name.endsWith('s')) {
            name = name[0..-2]
            methodname += 's'
        }
        if (name.size() == 1) name = name as char
        def result = InvokerHelper.getMetaClass(inner).invokeMethod(inner, methodname, name)
        if (null == result) result = inner.hasOption(name)
        if (result instanceof String[]) result = result.toList()
        return result
    }
    List arguments() {
        inner.getArgs().toList()
    }
}
