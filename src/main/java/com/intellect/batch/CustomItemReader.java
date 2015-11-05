package com.intellect.batch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class CustomItemReader implements ItemReader<String>{
	
	private List<String> list;
	
	private Iterator<String> it;

	public void setList(List<String> list) {
		this.list = list;
	}
	
	@PostConstruct
	public void init(){
		list = new ArrayList();
		String d1 = new String();
		String d2 = new String();
		String d3 = new String();
		list.add(d1);list.add(d2);list.add(d3);
		it = list.iterator();
		}

	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		
/*HttpServletRequest req = new MockHttpServletRequest();
		
		
		NSDictionary dx = new _EOFlatMutableDictionary();
		
		dx.takeValueForKey("recordID", "8010245e71d9f012cbe8257a1007fd2");
      
	//	EODatabaseContext c = new EODataba
		
		req.getSession().getServletContext().setAttribute("site", dx);
		
		InfoManagerDAO dao = new InfoManagerDAO(req);
	
		IContentText con = dao.getStringByDocId("ITG357");
		System.out.println("print isssssssssssssssssssssss"+con);*/
        if (it.hasNext()) {
            return it.next();
        }
		return null;
	}

}
