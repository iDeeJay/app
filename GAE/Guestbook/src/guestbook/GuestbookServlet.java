package guestbook;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

public class GuestbookServlet extends HttpServlet {
	public static List<String> ids = new LinkedList<String>();
	public static int ile = 0;
	  private static final Logger log = Logger.getLogger(GuestbookServlet.class.getName());
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	ChannelService channelService = ChannelServiceFactory.getChannelService();
    	String id = "" + ile;
    	ids.add(id);
    	ile++;
    	  String token = channelService.createChannel(id);
    	  
    	log.info("An informational message.");
        resp.setContentType("text/plain");
        resp.getWriter().print(token);
       

    }
}