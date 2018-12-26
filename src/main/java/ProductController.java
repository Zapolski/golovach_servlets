import by.zapolski.eshop.dao.ProductDao;
import by.zapolski.eshop.dao.exception.DaoSystemException;
import by.zapolski.eshop.dao.exception.NoSuchEntityException;
import by.zapolski.eshop.dao.impl.ProductDaoMock;
import by.zapolski.eshop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductController extends HttpServlet {
    public static final String PARAM_ID = "id";//имена параметров в запросе
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "product";
    public static final String PAGE_OK = "product.jsp";
    public static final String PAGE_ERROR = "error.jsp";

    //DAO Data Access Object
    private ProductDao productDao = new ProductDaoMock();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //параметры: то что приходит в запросе ?id=2&name=ivanov и т.д.
        //параметры - это Map<String,String>
        String idStr = request.getParameter(PARAM_ID);
        if (idStr!=null){
            try {
                Integer id = Integer.valueOf(idStr);
                //Product model = productDao.selectById(id);
                Product model = productDao.selectById(id);
                request.setAttribute(ATTRIBUTE_MODEL_TO_VIEW,model);
                //ok
                request.getRequestDispatcher(PAGE_OK).forward(request,response);
                return;
            } catch (NumberFormatException ex){//если id не строка
                /*NOP*/
            } catch (NoSuchEntityException ex){//не вытащили товар
                /*NOP*/
            }catch (DaoSystemException ex){//системная ошибка базы данных
                /*NOP*/
            }
        }
        //FAIL
        response.sendRedirect(PAGE_ERROR);
    }
}
