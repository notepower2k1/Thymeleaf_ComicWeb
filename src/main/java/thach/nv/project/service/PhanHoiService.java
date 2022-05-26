package thach.nv.project.service;

import java.util.List;

import thach.nv.project.entity.PhanHoi;

public interface PhanHoiService {
	List<PhanHoi> getAllPhanHoi();
	PhanHoi savePhanHoi(PhanHoi phanhoi_id);
	void removePhanHoi(int phanhoi_id);
	PhanHoi getPhanHoiById(int phanhoi_id);
}
