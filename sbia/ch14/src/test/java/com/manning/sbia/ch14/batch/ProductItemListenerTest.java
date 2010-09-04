/**
 *
 */
package com.manning.sbia.ch14.batch;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.item.file.FlatFileItemWriter;

import com.manning.sbia.ch14.domain.Product;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Unit with mock.
 *
 * @author bazoud
 *
 */
public class ProductItemListenerTest {
  private Product p = null;
  private FlatFileItemWriter<Product> writer = null;
  private List<Product> items = null;

  @Before
  public void setUp() {
    p = new Product();
    p.setId("211");
    p.setName("BlackBerry");
    items = new ArrayList<Product>();
    items.add(p);
    writer = mock(FlatFileItemWriter.class);
  }

  @Test
  public void testAfterProcess() throws Exception {
    ProductItemListener listener = new ProductItemListener();
    listener.setExcludeWriter(writer);
    listener.afterProcess(p, null);
    verify(writer, times(1)).write(items);
  }

  @Test
  public void testAfterProcessResult() throws Exception {
    ProductItemListener listener = new ProductItemListener();
    listener.setExcludeWriter(writer);
    listener.afterProcess(p, p);
    verify(writer, never()).write(items);
  }
}
