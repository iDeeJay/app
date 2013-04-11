package guestbook;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

public class ChannelTestServlet extends HttpServlet {

	private static String[] arr = {"track1", "track2", "track3", "track4", "track5", "track6"};
	
	
	
	private String dajTrackIdPlaylisty(String[] array) {
		String result = "";
		for (String str: array) {
			result += str;
			result += ";";
		}
		return result.substring(0, result.length()-1);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("NOTHING");

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String content = req.getParameter("content");
		if (content.equals("dajPlayliste")) {
			content = "p;" + dajTrackIdPlaylisty(arr);
		} else {
			//content
			int index = java.util.Arrays.asList(arr).indexOf(content);
			if (index != 0) {
				String tmp = arr[index-1];
				arr[index-1] = arr[index];
				arr[index] = tmp;
			}
			content = "up;" + content;
		}
		ChannelService channelService = ChannelServiceFactory
				.getChannelService();

		for (String str: GuestbookServlet.ids)
		channelService.sendMessage(new ChannelMessage(str,
				content));

		resp.setContentType("text/plain");
		resp.getWriter().println(content);

	}
}
