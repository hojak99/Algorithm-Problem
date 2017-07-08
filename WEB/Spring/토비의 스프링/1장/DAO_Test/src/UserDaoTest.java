import java.sql.Connection;
import java.sql.SQLException;
import java.text.Annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ApplicationContext context =
				new AnnotationConfigApplicationContext(DaoFactory.class);
		
		
		/*
		 * getBean() �޼ҵ�� ApplicationContext �� �����ϴ� ������Ʈ�� ��û�ϴ� �޼ҵ��̴�.
		 * "userDao" �� ApplicationContext �� ��ϵ� ���� �̸�
		 * */
		UserDao dao = context.getBean("userDao", UserDao.class);
	}

}
