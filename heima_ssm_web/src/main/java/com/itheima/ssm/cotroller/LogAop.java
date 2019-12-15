package com.itheima.ssm.cotroller;


import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogeService;
import com.itheima.ssm.service.impl.SysLogServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    private Date visitTime;
    private  Class clazz;
    private Method method;

    @Autowired
    private ISysLogeService sysLogService;


    //前置通知 获取开始时间 执行的类是哪一个 执行的哪一个方法
    @Before("execution(* com.itheima.ssm.cotroller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {

        visitTime=new Date();//获取访问时间
        clazz=jp.getTarget().getClass();//获取访问的类。
         String  methodName =jp.getSignature().getName(); //获取访问方法的名字；
        Object[] args = jp.getArgs();//获取访问的方法的参数
        if(args == null||args.length==0){
            method =clazz.getMethod(methodName);
        }else {
            Class[] classArgs = new Class[args.length];
            for(int i=0;i<args.length;i++){
                classArgs[i] =args[i].getClass();
            }
           method = clazz.getMethod(methodName,classArgs);
        }


    }
    @After("execution(* com.itheima.ssm.cotroller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {

        long time = new Date().getTime() - visitTime.getTime();

        String url="";
        if(clazz!=null&&method!=null&&clazz!=LogAop.class){
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                String[] classValue = classAnnotation.value();

                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();


                    url=classValue[0]+methodValue[0];
                    //获取访问的ip
                    String ip = request.getRemoteAddr();
                    //获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获了当前登录的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);

                    //调用service完成操作
                    sysLogService.save(sysLog);

                }

            }
        }



    }
}
