package com.example.semana8.controller;

import com.example.semana8.model.Category;
import com.example.semana8.model.Editor;
import com.example.semana8.service.implement.EditorServiceImplement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/editor/")
public class EditorController {

    private final EditorServiceImplement editorServiceImplement;

    public EditorController(EditorServiceImplement editorServiceImplement) {
        this.editorServiceImplement = editorServiceImplement;
    }

    @GetMapping()
    public List<Editor> getListAllEditors(){
        return editorServiceImplement.getAllEditors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editor> getEditorById(@PathVariable Long id){
        Editor editorSearch = editorServiceImplement.getEditorById(id);
        return ResponseEntity.ok(editorSearch);
    }

    @PostMapping()
    public ResponseEntity<Editor> createEditor(@RequestBody Editor editor){
        Editor editorCreated = editorServiceImplement.saveEditor(editor);
        return new ResponseEntity<>(editorCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editor> updateEditor(@PathVariable Long id, @RequestBody Editor editor ){
        Editor editorUpdated = editorServiceImplement.updateEditor(id, editor);
        return ResponseEntity.ok(editorUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEditor(@PathVariable Long id){
        String message = editorServiceImplement.deleteEditor(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
