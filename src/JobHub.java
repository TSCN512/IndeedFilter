import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class JobHub
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String search = "";
        for(String word: args)
            search += word +"+";

        search = search.substring(0, search.length() - 1);
        System.out.println("Pick a job site: \nIndeed(1)");
        switch(input.nextInt())
        {
            case 1:
            {
                System.out.println("Which city are you looking at today? \nNewark(1)\nJersey City(2)\nSeacaucus(3)\nWeehawken(4)\nRoseland(5)\nEdison(6)\nHoboken(7)\nAll(8)");

                indeed(input.nextInt(), search);
                while(true)
                {
                    System.out.println("See More? \nNewark(1)\nJersey City(2)\nSeacaucus(3)\nWeehawken(4)\nRoseland(5)\nEdison(6)\nHoboken(7)\nAll(8)\nExit(0)");
                    int choice = input.nextInt();
                    if(choice==0)
                        break;
                    else
                        indeed(choice, search);

                }
                break;
            }

            default:
            {
                System.out.println("Come on, try again");
                break;
            }
        }
    }
    public static void indeed(int choice, String search)
    {
        System.out.println("Here are your indeed job listings." + '\n');
        HashMap<String, String> cities = new HashMap<>();
        switch(choice)
        {

            case 1:
            {
                cities.put("Newark", "https://www.indeed.com/jobs?q="+search+"&rbl=Newark,+NJ&jlid=fc0723f9333e9830&sort=date");
                break;
            }
            case 2:
            {
                cities.put("Jersey City", "https://www.indeed.com/jobs?q="+search+"&rbl=Jersey+City,+NJ&jlid=a8725547cbf3b706&sort=date");
                break;
            }
            case 3:
             {
                cities.put("Seacaucus", "https://www.indeed.com/jobs?q="+search+"&rbl=Secaucus,+NJ&jlid=9c2227695c505787&sort=date");
                break;
            }
            case 4:
            {
                cities.put("Weehawken", "https://www.indeed.com/jobs?q="+search+"&rbl=Weehawken%2C%20NJ&jlid=61753ac7ba77bf38&sort=date");
                break;
            }
            case 5:
            {
                cities.put("Roseland", "https://www.indeed.com/jobs?q="+search+"&rbl=Roseland,+NJ&jlid=218092364ca6bfe9&sort=date");
                break;
            }
            case 6:
            {
                cities.put("Edison", "https://www.indeed.com/jobs?q="+search+"&rbl=Edison,+NJ&jlid=6978f3e2f6212bdf&sort=date");
                break;
            }
            case 7:
            {
                cities.put("Hoboken", "https://www.indeed.com/jobs?q="+search+"&rbl=Hoboken,+NJ&jlid=9b1805cecf09cc58&sort=date");
                break;
            }
            default:
            {
                cities.put("Newark", "https://www.indeed.com/jobs?q="+search+"&rbl=Newark,+NJ&jlid=fc0723f9333e9830&sort=date");
                cities.put("Jersey City", "https://www.indeed.com/jobs?q="+search+"&rbl=Jersey+City,+NJ&jlid=a8725547cbf3b706&sort=date");
                cities.put("Seacaucus", "https://www.indeed.com/jobs?q="+search+"&rbl=Secaucus,+NJ&jlid=9c2227695c505787&sort=date");
                cities.put("Weehawken", "https://www.indeed.com/jobs?q="+search+"&rbl=Weehawken%2C%20NJ&jlid=61753ac7ba77bf38&sort=date");
                cities.put("Roseland", "https://www.indeed.com/jobs?q="+search+"&rbl=Roseland,+NJ&jlid=218092364ca6bfe9&sort=date");
                cities.put("Edison", "https://www.indeed.com/jobs?q="+search+"&rbl=Edison,+NJ&jlid=6978f3e2f6212bdf&sort=date");
                cities.put("Hoboken", "https://www.indeed.com/jobs?q="+search+"&rbl=Hoboken,+NJ&jlid=9b1805cecf09cc58&sort=date");
                break;
            }
        }
            for (String city : cities.keySet())
            {
                System.out.println("Listings for " + city + ": ");
                try
                {
                    Document jobPage = Jsoup.connect(cities.get(city)).get();
                    Elements titleListings = jobPage.getElementsByClass("jobTitle");
                    for (Element job : titleListings)
                    {
                        if (job.lastElementSibling().toString().contains("sponsoredGray"))
                            continue;
                        System.out.println(job.toString().replaceAll("<[^>]*>", ""));
                        System.out.println('\t' + job.lastElementSibling().select("span.summary").toString().replaceAll("<[^>]*>", "") + '\n');

                    }

                }
                catch (IOException e)
                {
                    System.out.println("Got wrong");
                }
            }

    }
}

