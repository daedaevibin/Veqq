# Veqq — Design Draft

New system, not a shiggy rework. Internal-only. No cross-compatibility with other clients (no `__SHIGGY_LOADER__`, no `__vendetta_loader`, no `__PYON_LOADER__`, no `__RAIN_LOADER__`).

Approach: Vector framework (Zygisk / LSPatch) as the hook layer, rebuilt from scratch.

## License
OSL 3.0 only (`LICENSE` — full text from SPDX). No other licensing.

## Source References (Verified)
- Vector: https://github.com/JingMatrix/Vector (ART hooking framework, Zygisk module, Android 8.1–17)
- LSPatch: https://github.com/JingMatrix/LSPatch (rootless APK patch, embeds loader/framework runtime, uses same LSPlant engine)

## Why not current shiggy approach
- Shiggy uses Xposed injection (`Shiggy.bundle`, `ShiggyXposed`) which Samsung Knox / Defex fights via memory scanning.
- Shiggy bundle (`dist/shiggycord.js`) relies on `metro` caches, deferred `AppRegistry` patches (`entry.ts`), and external plugin APIs (`core/plugins/index.ts`, `lib/addons/plugins/`).
- Cross-loader identity (`loader.ts`) and theme/font support (`fontPatch === 2`) are tied to other clients (Pyon, Rain, Vendetta).

## Internal Design Rules (Fixed)
1. **No external loader identity.** No `globalThis.__SHIGGY_LOADER__`, no polyfill for other clients.
2. **No external plugin dependency.** No `core/plugins/` registry importing external module repositories. Plugins, if any, are internal-only and bundled at build time.
3. **Rootless primary path.** LSPatch-style APK rewrite (what `ShiggyManager` already uses: `org.lsposed.patch.LSPatch`). Vector (Zygisk) is the secondary path if root is available.
4. **Bundle architecture rebuilt.** Instead of `entry.ts` deferred `batchedBridge` patches against Discord's Metro internals, Veqq embeds its own runtime directly via the patched APK loader.
5. **No shiggy file reuse.** `README.md`, `entry.ts`, `loader.ts`, `core/plugins/`, `dist/` are not copied.

## Verified Workspace State (Shiggy, Unchanged)
- `ShiggyCord`: `1.4.2.0`, `main` branch, clean tree.
- `ShiggyManager`: `1.1.2` / code `101201`, uses `LSPatch` (`InjectShiggyXposedStep.kt`).
- `ShiggyXposed`: `master`, 19 unpushed commits, `1.4.2.0`.
- APKs present in scratch: `349009.apk` (`349.9`), `350008.apk` (`350.8` — last good), `351011.apk` (`351.11` — broken for most, works for root/user).
- No `342016` APK/reference in workspace; user reports it as best but it is not present here.

## Draft Structure (To Be Built)
```
veqq/
  loader/          # Internal loader replacement (no loader identity polyfill)
  bundle/          # Build-time bundle (not Metro-patched at runtime)
  patch/           # LSPatch integration (internal version of InjectShiggyXposedStep)
  internal/        # Internal plugins / fixes (replaces external plugin registry)
  build/           # Build scripts (replaces build.mjs / bundle version hardcoding)
  docs/            # Design decisions (evidence-cited only)
```

## Next Step
Design `loader/` internal architecture (how bundle loads without external loader identity) and `patch/` LSPatch step sequence.
