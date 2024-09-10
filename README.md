## URL							Servlet							JSP
+ /auth/login.html			---		LogInController			---		/auth/LoginForm.jsp
+ /auth/logout.html			---		LogOutController		---		/auth/LoginFail.jsp
+ /member/list.html			---		MemberListController		----	/member/MemberList.jsp
+ /member/update.html?id=		---		MemberUpdateController		----	/member/MemberUpdateForm.jsp
+ /member/add.html			---		MemberAddController		----		/member/MemberForm.jsp
+ /member/delete.html?id=		---		MemberDeleteController		----	/member/MemberList.jsp


## Filter
-	url mapping 
-	request --> Filter --> Filter --> Servlet 
				|FilterChain|

## Listener
- event binding
	+	Web Application(Context)'s state , object, data
1.	implements Listener interface
+	choice Interface according event 
2.	regist your Listener in web.xml (or annotation)
+	manage Listener object by Container
+	<Listener> , @WebListener
3.	call method of Listener when occurred event (change state of Web application)
+	according interface




Container watch web application 
	+create , destroy, change --> invoke Event
	+if has Listener, call method of Listener

Storage save object, config value, data as "Attribute's"
	ServletContext Listener when web application start, shutdown
	ServletRequest Listener when create request
	HttpSession Listenner 	when create, invalidate session 
	+	Container (Tomcat, JBOSS, ...... many)





## MVC framework
+	base package : com.coding.mvc
+	Model : Class type, DB, file
+ 	View : screen, web(html), GUI(Graphic User Interface) window
+	Controller : processing, logic,
+	according Role ( part of application )

Java 	
	Controller -> Service -> DAO(Repository)

## Dispatcher Servlet as Front Controller
prev : request URL -> Servlet ( one by one )
	+	code lap lai. rut ngan nhat co the
	+	request , response
+	logic 
	+ 	jsp, html -> redirect , forward

URL 
	*.do	-->	Dispatcher
	html, css, js, jpg, --> directly file static
	directory/	-->	/	Welcome file (index html)
 
role of DispatcherServlet
 
 1. receive all request call "Font Controller"
 	+	URL ?
	+	select controller
 2. logic --> delegate to Controller.execute() with Interface
 	+ if need data, pass data to controller
 	+ return result , view (jsp)
 3.response
 	+view -> response
  
## Data Binding 
current : DispatcherServlet 
URL : request parameter, map(model)
	URL Controller, DispatcherServlet
+request parameter 
	-automatically set

#####[Browser] [DispatcherServlet] [Controller] [ServletRequestDataBinder] [Map model]
	(request)			(Map model)
	(1) request
			(2)select Controller
						(3).getDataBinder()
			(4) .bind(request, clazz)
			(5) put()
						(6) .execute()
						
## Content need remember
+ application-context.properties Đọc tệp và tạo Hashtable để chứa giá trị key và value
+ key : jndi.dataSource, memberDAO, /auth/login.html ....
+ value : java:comp/env/jdbc/studydb, com.store.dao.MemberDAO, com.store.controls.LoginController...
