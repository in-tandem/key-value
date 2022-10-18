package com.org.somak.store.keyvalue.tool;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.text.WordUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


/***
 *
 */
public class SourceCodeGenerator {

    private static final String itemLocation ="src/main/java/com/org/somak/store/keyvalue/dto/";
    private static final String itemFactoryLocation = "src/main/java/com/org/somak/store/keyvalue/factory/";
    private static final String itemTemplateLocation = "src/main/resources/templates/item.ftl";
    private static final String itemFactoryTemplateLocation = "src/main/resources/templates/itemfactory.ftl";

    private static Template template = null;

    public static void main(String[] args) throws IOException {

        if(args==null || (args!=null && args.length==0))
            throw new IllegalArgumentException("Please provide single word alphabetical string");

        Configuration cfg = new Configuration();
//        cfg.setClassForTemplateLoading(SourceCodeGenerator.class, "resources//templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        for(String userInput:args){
            System.out.println(userInput);
            if(Pattern.compile("^[A-Za-z]+$").matcher(userInput).matches()){
                String itemName = WordUtils.capitalize(userInput.toLowerCase());
                String itemFactoryName = WordUtils.capitalize(itemName)+"Factory";

                Map<String, String> input = new HashMap<String, String>();
                input.put("name",itemName);
                input.put("itemName",itemName);
                input.put("factoryName",itemFactoryName);

                generateSourceCode(cfg, itemTemplateLocation,itemLocation+"//"+ itemName+".java", input);
                generateSourceCode(cfg, itemFactoryTemplateLocation, itemFactoryLocation+"//"+ itemFactoryName+".java", input);
            }
            else
                throw new IllegalArgumentException("Please provide proper input. It needs to be a single word ");

        }

    }

    private static void generateSourceCode(Configuration cfg,
                                           String templateLocation,
                                           String sourceCodeLocation,
                                           Map<String, String> input) throws IOException {
        template = cfg.getTemplate(templateLocation);



        try(FileWriter fileWriter = new FileWriter(new File(sourceCodeLocation))){
            template.process(input, fileWriter);
            fileWriter.flush();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }


}
