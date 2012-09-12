package my.andr.share.lotto;

import java.util.Random;

public class Lotto {
	private int[] values = new int[6]; // 6���� ���� �����ϱ� ���� �迭
	private Random ran = new Random(); // ������ ���� �����ִ� Ŭ����

	public void setValues() { // �ߺ����� ���� ������ �� 6���� ���Ͽ� ����.
		for (int i = 0; i < values.length; i++) {
			values[i] = uniqueValue(i);
		}
	}

	// ������ �� 6���� ���Ͽ� �����Ѵ�.
	public StringBuilder getValues() {
		setValues(); // inputValues()�� ȣ���Ͽ� ������ �� 6���� ���Ѵ�.
		// ������ 6���� ���� StringBuilder�� ��Ƽ� ������� ����
		StringBuilder sb = new StringBuilder();
		// values �迭���� �ϳ��� �����͸� ������ s�� ����(Java5.0���� �߰�)
		for (int s : values) { // values�� ������ŭ �ݺ�ó�� �Ѵ�.
			sb.append(s + " ");
		}
		return sb;
	}

	// �ߺ����� ���� ������ ���� ã�Ƽ� �����Ѵ�
	private int uniqueValue(int index) { // ���� ���ϰ��� �ϴ� ��ġ�� �ĺ�
		int imsi = 0;
		boolean eq = false; // �ߺ��� �� ����
		do {
			imsi = ran.nextInt(45) + 1; // ������ ���� ���Ͽ�
			eq = false;
			// �����ϴ� ���� �ߺ����� �ʴ����� ��
			for (int j = 0; j < index; j++) {
				if (imsi == values[j]) {
					eq = true;
					break;
				}
			}
		} while (eq); // �ߺ� �Ǿ��ٸ� �ٽ� do loop ����
		return imsi; // �ߺ����� �ʾҴٸ� �� ����
	}

}
