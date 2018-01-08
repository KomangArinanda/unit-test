
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.internal.matchers.Matches;
import org.springframework.stereotype.Service;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;
import springboot.service.TodoService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
@Service
public class TodoServiceTest {

    //instatiate TodoService

    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Before
    public void setUp(){
        this.todoRepository = new TodoRepository();
        MockitoAnnotations.initMocks(this);//ini yang akan di test yaitu todoService
        this.todoService = new TodoService(this.todoRepository);
    }

    @After
    public void setEnd(){
        Mockito.verifyNoMoreInteractions(this.todoRepository);
    }

    @Test
    public void getAllTest() throws Exception{
        ArrayList<Todo> todos = new ArrayList<Todo>();
        todos.add(new Todo("todo1", TodoPriority.MEDIUM));

        BDDMockito.given(todoRepository.getAll()).willReturn(todos);
//        todoRepository.store(new Todo("todo1", TodoPriority.MEDIUM));
        List<Todo> todoList = todoService.getAll();

        Assert.assertThat(todoList, org.hamcrest.Matchers.notNullValue());
        Assert.assertThat(todoList.isEmpty(), org.hamcrest.Matchers.equalTo(false));

        BDDMockito.then(todoRepository).should().getAll();


//        if(todoList.isEmpty()){
//            throw new Exception("List Kosong");
//        }else {
//            System.out.println("List Ada Isinya");
//        }
    }

//    @After
//    public void setEndSaveToDo(){
//        Mockito.verifyNoMoreInteractions(this.todoRepository);
//    }
    @Test
    public void saveTodo() throws Exception{
        todoRepository.store(new Todo("todo3",TodoPriority.MEDIUM));
        BDDMockito.then(todoRepository).should().store(new Todo("todom3", TodoPriority.MEDIUM));
    }

}
