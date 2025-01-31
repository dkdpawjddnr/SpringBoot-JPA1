package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService { // 상품 서비스는 상품 레포지토리를 단순하게 위임만 하는 서비스

    private final ItemRepository itemRepository;

    @Transactional // readOnly = true라 저장이 안됨 그래서 @Transactional 한거임
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem (Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId); // Id를 기반으로 실제 DB에 있는 영속상태의 Entity를 찾아옴
        // set을 사용하는 것보다는 실무에서는 메서드를 따로 만들어서 변경하는게 훨 나음 ex) findItem.change(name,...)
        findItem.setName(name); // 데이터 수정
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
        // 트랜젝션에 의해서 commit이 됨. JPA가 업데이트 해줌.
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
