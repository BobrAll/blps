package bobr.blps_lab1.businessLogic.flat;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class FlatService {
    private final FlatRepository flatRepository;

    public List<Flat> findAll() {
        return flatRepository.findAll();
    }

    public void save(Flat flat) {
        flatRepository.save(flat);
    }

    public void delete(Integer id) {
        flatRepository.deleteById(id);
    }
}
