/*
	16������ �Է¹޾� 8������ ����ϱ�.
*/

#include <iostream>

int main()
{
	int inputNumber;

	/*
		8����  : std::oct
		10���� : std::dec
		16���� : std::hex
	*/


	// 16������ �Է� �ޱ�
	std::cin >> std::hex >> inputNumber;
	
	// 8������ ���
	std::cout << std::oct << inputNumber;

	return 0;
}