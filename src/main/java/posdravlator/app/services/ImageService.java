package posdravlator.app.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import com.mongodb.client.gridfs.model.GridFSFile;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class ImageService {
    @Autowired
    private GridFsOperations gridFsOperations;

    public String uploadImage(MultipartFile file) throws IOException {
        return gridFsOperations.store(
            file.getInputStream(),
            file.getOriginalFilename(),
            file.getContentType()
        ).toString();
    }

    public byte[] getImage(String id) throws IOException {
        GridFSFile gridFSFile = gridFsOperations.findOne(query(where("_id").is(id)));

        if (gridFSFile != null) {
            return gridFsOperations.getResource(gridFSFile).getContent().readAllBytes();
        }

        throw new EntityNotFoundException("Фото не найдено.");
    }

    public String getImageType(String imageId) {
        if (imageId.endsWith(".png")) {
            return "image/png";
        }
        else if (imageId.endsWith(".jpg") || imageId.endsWith(".jpeg")) {
            return "image/jpeg";
        }
        else {
            return "application/octet-stream";
        }
    }
}