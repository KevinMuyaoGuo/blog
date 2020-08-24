package com.gmy.util;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.*;

/**
 * @author Kevin Guo
 * @package com.gmy.util
 * @date 2020/8/24 15:05
 * @description
 */
public class MarkdownUtils {


    public static String markdownToHtml(String markdown) {
        /**
         * @author: Kevin Guo
         * @date: 2020/8/24 15:10
         * @param: markdown
         * @return: {@link String}
         * @description: Markdown 格式转换成 HTML 格式
         */
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    public static String markdownToHtmlExtensions(String markdown) {
        /**
         * @author: Kevin Guo
         * @date: 2020/8/24 15:26
         * @param: markdown
         * @return: {@link String}
         * @description: 增加扩展 [标题锚点，表格生成]，Markdown 转化成 HTML
         */
        //h标题生成id
        Set<Extension> headingAnchorExtensions = Collections.singleton(HeadingAnchorExtension.create());
        //转换table的HTML
        List<Extension> tableExtensions = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder().extensions(tableExtensions).build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(headingAnchorExtensions)
                .extensions(tableExtensions)
                .attributeProviderFactory(new AttributeProviderFactory() {
                    @Override
                    public AttributeProvider create(AttributeProviderContext context) {
                        return new CustomAttributeProvider();
                    }
                })
                .build();
        return renderer.render(document);
    }

    /**
     * @author: Kevin Guo
     * @date: 2020/8/24 15:26
     * @param: null
     * @return: {@link }
     * @description: 处理标签的属性
     */
    static class CustomAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            //改变a标签的target属性为_blank
            if (node instanceof Link) {
                attributes.put("target", "_blank");
            }
            if (node instanceof TableBlock) {
                attributes.put("class", "ui celled table");
            }
        }
    }

}
