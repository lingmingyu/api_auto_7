package test.util;

import test.cases.Case;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/*Case用例工具类
* */
public class CaseUtil {
    //保存所有的用例对象(共享数据)
    public static List<Case> cases= new ArrayList<Case>();
    static {
        //将所有数据解析封装到ceses
        ExcelUtil.load("src/test/resources/cases_v7.xls","用例", Case.class);

    }
    /*根据接口编号拿对应接口的测试数据
     * apiId 指定接口编号
     * cellNames 要获取的数据对应的列名
     * */
    public  static  Object [] [] getCaseDatesByApiId(String apiId,String [] cellNames) {
        Class<Case> clazz = Case.class;
        //保存指定接口编号的case对象的集合
        ArrayList<Case> csList = new ArrayList<>();
        //通过循环找出指定接口编号对应的这些用例数据
        for (Case cs : cases) {
            //循环处理
            if (cs.getApiId().equals(apiId)) {
                csList.add(cs);

            }
        }
        Object[][] datas = new Object[csList.size()][cellNames.length];
        for (int i = 0; i < csList.size(); i++) {
            Case cs = csList.get(i);
            for (int j = 0; j < cellNames.length; j++) {
                //要反射的方法名
                String methodName = "get" + cellNames[j];
                //获取要反射的方法对象
                Method method = null;
                try {
                    method = clazz.getMethod(methodName);
                    String value = (String) method.invoke(cs);
                    datas[i][j] = value;
                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        }
        return datas;
    }

//    public static void main(String[] args) {
//        String [] cellNames = {"Params"};
//        Object [] [] datas =getCaseDatesByApiId("2",cellNames);
//        for (Object[] objects :datas){
//            for (Object object:objects){
//                System.out.println("["+object+"]");
//            }
//            System.out.println();
//        }
//    }


}
