package pironeer3rd.SpringbootBoard.global.service;

import org.springframework.stereotype.Component;
import pironeer3rd.SpringbootBoard.global.dto.response.result.ListResult;
import pironeer3rd.SpringbootBoard.global.dto.response.result.SingleResult;

import java.util.List;

@Component
public class ResponseService {
  public static <T> SingleResult<T> getSingleResult(T data) {
    SingleResult<T> result = new SingleResult<>();
    result.setData(data);
    return result;
  }

  public static <T> ListResult<T> getListResult(List<T> list) {
    ListResult<T> result = new ListResult<>();
    result.setList(list);
    return result;
  }
}
