package com.example.semana8.service;

import com.example.semana8.model.Editor;

import java.util.List;

public interface EditorService {

    public List<Editor> getAllEditors();

    public Editor getEditorById(Long id);

    public Editor saveEditor(Editor editor);

    public Editor updateEditor(Long id, Editor editor);

    public String deleteEditor(Long id);

    public Boolean existsEditorById(Long id);

}
