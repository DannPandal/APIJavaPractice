package com.example.semana8.service.implement;

import com.example.semana8.exception.ExceptionNotFoundEntity;
import com.example.semana8.model.Author;
import com.example.semana8.model.Editor;
import com.example.semana8.repository.EditorRepository;
import com.example.semana8.service.EditorService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
public class EditorServiceImplement implements EditorService {

    private final EditorRepository editorRepository;

    public EditorServiceImplement(EditorRepository editorRepository) {
        this.editorRepository = editorRepository;
    }
    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public List<Editor> getAllEditors() {
        Optional<List<Editor>> editors = editorRepository.findAllWithStatusActive();
        if (editors.isPresent()) {
            return editors.get();
        }
        throw new ExceptionNotFoundEntity("Authors not found");
//        return editorRepository.findAll();
    }

    @Override
    @ExceptionHandler(ExceptionNotFoundEntity.class)
    public Editor getEditorById(Long id) {
        Optional<Editor> editor = editorRepository.findByIdAndStatusNot(id, 0);
        if (editor.isPresent()) {
            return editor.get();
        } else {
            throw new ExceptionNotFoundEntity("Editor not found");
        }
    }

    @Override
    public Editor saveEditor(Editor editor) {
        editor.setStatus(editor.getStatus() == null ? 1 : editor.getStatus());
        return editorRepository.save(editor);
    }

    @Override
    public Editor updateEditor(Long id, Editor editor) {
        if (existsEditorById(id)){
            Editor existingEditor = getEditorById(id);
            existingEditor.setId(id);
            existingEditor.setName(editor.getName());
            existingEditor.setStatus(editor.getStatus() == null ? 1 : editor.getStatus());
            return editorRepository.save(existingEditor);
        }
        return null;
    }

    @Override
    public String deleteEditor(Long id) {
        if (existsEditorById(id)){
            Editor existingEditor = getEditorById(id);
            existingEditor.setStatus(0);
            editorRepository.save(existingEditor);
            return "Editor removed !! ";
        }
        return "Editor not found !! ";
    }

    @Override
    public Boolean existsEditorById(Long id) {
        return editorRepository.existsByIdAndStatusNot(id, 0);
    }
}
