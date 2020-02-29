package com.example.timesup.util;

import android.content.Context;
import android.os.AsyncTask;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lombok.Getter;

@Getter
class DropboxFileReader extends AsyncTask<String, Void, List<String>> {

    Context context;
    List<String> result;

    private static final String FILENAME = "cards.txt";
    private static final String ACCESS_TOKEN = "jHGdwQHaJmAAAAAAAAAAERuc56r6YRiWnj68YZgKj5hKzy2RgJwhQBnfrd8zLFG0";

    public DropboxFileReader(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected List<String> doInBackground(String... urls) {
        try {
            DbxRequestConfig dbxRequestConfig = DbxRequestConfig.newBuilder("dropbox/my_folder").build();
            DbxClientV2 dbxClientV2 = new DbxClientV2(dbxRequestConfig, ACCESS_TOKEN);
            Optional<FileMetadata> cardsFileMetadata = dbxClientV2.files()
                    .listFolderBuilder("")
                    .withRecursive(true)
                    .withIncludeMediaInfo(true)
                    .start().getEntries().stream()
                    .filter(file -> FILENAME.equals(file.getName()))
                    .map(file -> (FileMetadata) file)
                    .findFirst();
            if (cardsFileMetadata.isPresent()) {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                dbxClientV2.files().download(cardsFileMetadata.get().getPathLower()).download(os);
                return Arrays.asList(new String(os.toByteArray()).split("\\r\\n"));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<String> feed) {
        this.result = feed;
    }
}