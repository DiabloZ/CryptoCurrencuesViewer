package com.suhov.cryptocurrencuesviewer.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.suhov.cryptocurrencuesviewer.R
import kotlinx.android.synthetic.main.fragment_test.*

class TestFragment:Fragment(R.layout.fragment_test) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var testText = "<!doctype html>\n<html lang=\"ru\">\n<head>\n    <meta charset=\"UTF-8\">\n    </head>\n<style>\n    @import url('http://fonts.cdnfonts.com/css/pt-root-ui');margin: 0px; padding: 0px\n    @font-face {\n        font-family: 'PT Root UI', sans-serif;\n    }\n    .title {\n   font-style: normal;\n margin:0px;" +
                "margin: 0 !important;padding: 0 !important;\">" +
                "\n padding: 0px;\n padding-right: 0px!!todp!;\n padding-left: 0px!!todp!; padding-right: 0px;\n" +
                 "  padding-left: 0px;\n" +
                 "  border-right-width: 0px;\n" +
                 "  border-left-width: 0px;\n" +
                 "  margin-right: 0px;\n" +
                 "  margin: 0px;\n" +
                 "  padding: 0px;\n" +

                 "  margin-left: 0px;    font-weight: bold;\n        font-size: 18!!tosp!px;\n        line-height: 20px;\n        font-feature-settings: 'case' on;\n        color: color: rgba(0, 0, 0, 0.0)!!!;\n        font-family: 'PT Root UI', sans-serif;\n    }\n    </style>\n<body>\n<div class=\"title\">\n    " +
                 "Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. " +
                 "\n</div>\n<div class=\"description\">\n </div>\n</body>\n</html>"
                 /*        testText = "test123"
        testText = "javascript:(function(){ document.body.style.paddingTop = '55px'})();"
        da_da_da.settings.javaScriptEnabled = true
        da_da_da.setScrollbarFadingEnabled(true)
        da_da_da.setPadding(0, 0, 0, 0);
        ;*/
                     //testText = "Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст"
        val testTEST = "<!DOCTYPE html>\n" +
                "\n" +
                "<html>\n" +
                "<style>\n" +
                "@import url('http://fonts.cdnfonts.com/css/pt-root-ui')\n" +
                "@font-face {font-family: 'PT Root UI', sans-serif;}\n" +
                ".main_content {   font-style: normal; padding-right: 0px; padding-left: 0px;     font-weight: normal;        font-size: 18px;        line-height: 20px;        font-feature-settings: 'case' on;        color: color: rgba(0, 0, 0, 0.0);        font-family: 'PT Root UI', sans-serif;    } \n" +
                "</style>\n" +
                "<body>\n" +
                "<body style=\"margin: 0 !important;padding: 0 !important; font-style: normal;\"></body>\n" +
                "<div class=\"main_content\">Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст.</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n" +
                "\n"
        da_da_da.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)
        da_da_da.loadData(testTEST, "text/html", "UTF-8")
        da_da_da2.loadData("Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст. Тестовый текст".toWebFormat(), "text/html", "UTF-8")

    }
}

fun String?.toWebFormat(paddingRight:Int = 0, paddingLeft: Int = 0, fontSize: Int = 18, bodyMargin:Int = 0, bodyPadding:Int = 0, bodyTextStyle: String = "normal"):String = when{
    this?.toLowerCase()?.contains("html") == true -> this
    else -> "<!DOCTYPE html>\n" +
            "\n" +
            "<html>\n" +
            "<style>\n" +
            "@import url('http://fonts.cdnfonts.com/css/pt-root-ui')\n" +
            "@font-face {font-family: 'PT Root UI', sans-serif;}\n" +
            ".main_content {   font-style: normal; padding-right: $paddingRight px; padding-left: $paddingLeft\\px;     font-weight: $bodyTextStyle;        font-size: $fontSize\\px;        line-height: 20px;        font-feature-settings: 'case' on;        color: rgba(0, 0, 0, 0.0)!!!;        font-family: 'PT Root UI', sans-serif;    } \n" +
            "</style>\n" +
            "<body>\n" +
            "<body style=\"margin: $bodyMargin !important;padding: $bodyPadding !important;\"></body>\n" +
            "<div class=\"main_content\">$this</div>\n" +
            "\n" +
            "</body>\n" +
            "</html>\n" +
            "\n"
}