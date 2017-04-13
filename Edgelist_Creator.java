import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.*;

public class Edge {
    public static void main(String[] args) {
        try {
            HashMap<String, String> fileUrlMap = new HashMap<String, String>();
            HashMap<String, String> urlFileMap = new HashMap<String, String>();

            BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\risha\\Desktop\\NBCNewsData\\mapNBCNewsDataFile.csv"));
            String line = "";
            while ((line = br1.readLine()) != null) {
                String[] array = line.split(",");
                fileUrlMap.put(array[0], array[1]);
                urlFileMap.put(array[1], array[0]);
            }
           
            br1.close();
            File dir = new File("C:\\Users\\risha\\Desktop\\NBCNewsData\\NBCNewsDownloadData\\");
            Set<String> edges = new HashSet<String>();
            for (File file : dir.listFiles()) {
                //System.out.println(file.getName());
                Document doc = Jsoup.parse(file, "UTF-8", fileUrlMap.get(file.getName()));
                Elements links = doc.select("a[href]");

                for (Element link : links) {
                    String url = link.attr("abs:href").trim();
                    if (urlFileMap.containsKey(url)) {
                        edges.add(file.getName() + " " + urlFileMap.get(url));
                    }
                }
            }

            PrintWriter printWriter = new PrintWriter("C:\\Users\\risha\\Desktop\\edgeList.txt");
            for (String s : edges) {
                printWriter.println(s);
            }
            printWriter.flush();
            printWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
