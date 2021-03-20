package mybatis;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import mybatis.model.Test1;

public class Test1For {
	
	private static SqlSessionFactory sessionFactory;
	 
	@BeforeAll
	public static void setUp() throws Exception
	{
		// create a SqlSessionFactory
		Reader reader = Resources.getResourceAsReader("mybatis/test1Config.xml");
		sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		reader.close();
 
		// populate in-memory database
		/*		SqlSession session = sqlSessionFactory.openSession();
		Connection conn = session.getConnection();
		reader = Resources.getResourceAsReader("config/mybatis/files/hqlbd.sql");
		ScriptRunner runner = new ScriptRunner(conn);
		runner.setLogWriter(null);
		runner.runScript(reader);
		reader.close();
		session.close();*/
	}
 
    @Test
    public void test(){
        //通过SqlSessionFactory打开一个会话
        SqlSession openSession = sessionFactory.openSession();
        try{
            List<Test1> dataList = openSession.selectList("selectAll");
            printCountryList(dataList);
        }finally{
            //关闭session会话
            openSession.close();
        }
    }
    
    private void printCountryList(List<Test1> dataList){
        for(Test1 item : dataList){
            System.out.println(item.getId()+"--"+item.getName()+"--"+item.getAge());
        }
    }
    
	

}
