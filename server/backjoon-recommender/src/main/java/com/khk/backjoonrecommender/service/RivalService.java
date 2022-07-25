package com.khk.backjoonrecommender.service;

import com.khk.backjoonrecommender.controller.dto.request.RivalSearchRequestDto;
import com.khk.backjoonrecommender.controller.dto.response.BasicResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.RivalListResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.RivalResponseDto;
import com.khk.backjoonrecommender.controller.dto.response.RivalSearchResponseDto;
import com.khk.backjoonrecommender.entity.Rival;
import com.khk.backjoonrecommender.entity.User;
import com.khk.backjoonrecommender.exception.AlreadyRegisteredException;
import com.khk.backjoonrecommender.exception.CannotAddRivalOneSelfException;
import com.khk.backjoonrecommender.exception.UserNotFoundException;
import com.khk.backjoonrecommender.repository.RivalRepository;
import com.khk.backjoonrecommender.repository.UserRepository;
import com.khk.backjoonrecommender.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.khk.backjoonrecommender.common.ResponseCodeMessage.SUCCESS;
import static com.khk.backjoonrecommender.common.ResponseCodeMessage.SUCCESS_USER_DETAIL;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RivalService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final RivalRepository rivalRepository;

    public BasicResponseDto<List<RivalListResponseDto>> findRivals(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        List<Rival> rivals = rivalRepository.findAllBySelectingUser(user);
        List<RivalListResponseDto> results = rivals.stream()
                .map(rival -> new RivalListResponseDto(rival.getSelectedUser().getId(), rival.getSelectedUser().getUsername()))
                .collect(Collectors.toList());
        return new BasicResponseDto<>(200, "RIVAL", results);
    }

    public BasicResponseDto<RivalSearchResponseDto> findRival(RivalSearchRequestDto rivalSearchRequestDto) {
        String username = rivalSearchRequestDto.getUsername();
        User findUser = userRepository.findByUsername(username);
        if (findUser == null) {
            throw new IllegalArgumentException("찾는 유저가 없습니다.");
        }
        RivalSearchResponseDto responseDto = new RivalSearchResponseDto(findUser.getId(), findUser.getUsername());
        return new BasicResponseDto<>(SUCCESS, SUCCESS_USER_DETAIL, responseDto);
    }

    @Transactional
    public BasicResponseDto<RivalResponseDto> addRival(Long rivalId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        Optional<User> optionalUser = userRepository.findById(rivalId);
        Rival selectedUser = rivalRepository.findSelectedUserById(rivalId);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }

        if (user.getId() == rivalId) {
            throw new CannotAddRivalOneSelfException();
        }

        if (selectedUser != null) {
            throw new AlreadyRegisteredException();
        }

        Rival rival = new Rival(user, optionalUser.get());
        rival.setUser(user);
        Rival savedRival = rivalRepository.save(rival);
        return new BasicResponseDto<>(200, "RIVAL", new RivalResponseDto(user.getId(), savedRival.getSelectedUser().getUsername()));
    }

    @Transactional
    public BasicResponseDto<?> deleteRival(Long rivalId) {
        Rival findRival = rivalRepository.findSelectedUserById(rivalId);
        rivalRepository.delete(findRival);
        return new BasicResponseDto<>(200, "RIVAL DELETE", null);
    }
}
